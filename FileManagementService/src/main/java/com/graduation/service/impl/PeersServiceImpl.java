package com.graduation.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.model.dto.PageInfoDto;
import com.graduation.model.pojo.Peers;
import com.graduation.mapper.PeersMapper;
import com.graduation.model.vo.ApiResultVo;
import com.graduation.service.IndexService;
import com.graduation.service.PeersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.utils.Constant;
import com.graduation.utils.FileSizeConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@Service
public class PeersServiceImpl extends ServiceImpl<PeersMapper, Peers> implements PeersService {

    @Autowired
    private IndexService indexService;


    @Override
    public PageInfoDto<Peers> listPage(int page, int limit) {
        PageInfoDto<Peers> result = new PageInfoDto<>();
        Page<Peers> peersPage = new Page<>(page,limit);
        this.page(peersPage);
        result.setTotal(peersPage.getTotal());
        result.setState(200);
        result.setData(peersPage.getRecords());
        return result;
    }

    @Override
    public List<Peers> getAllPeers() {
        return this.list();
    }

    @Override
    public boolean checkPeersAddressIsUseful(String serverAddress) {
        String res = HttpRequest.get(serverAddress+ Constant.API_STAT).timeout(2000).execute().body();
        ApiResultVo resultVo = JSONUtil.toBean(res, ApiResultVo.class);
        if (!Constant.API_STATUS_SUCCESS.equals(resultVo.getStatus())) {
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> getPeersStatus(String serverAddress) {
        String json = HttpUtil.get(serverAddress + Constant.API_STATUS);
        JSONObject jsonObject = JSONUtil.parseObj(json);
        if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
            return indexService.getStatus(jsonObject.get("data"));
        }
        return null;
    }

    @Override
    public Double getPeersLeftSpace(Integer peersId) {
        Peers peers = this.getById(peersId);
        return peers.getDiskLeftSize();
    }

    @Override
    public boolean updatePeersLeftSpace(Integer peersId, Double leftSpace) {
        UpdateWrapper<Peers> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",peersId);
        updateWrapper.set("disk_left_size",leftSpace);
        return this.update(updateWrapper);
    }

    @Override
    public boolean checkPeersExist(String serverAddress) {
        QueryWrapper<Peers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_address", serverAddress);
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean addPeers(Peers peers) {
        String peersUrl;
        if (!StringUtils.isBlank(peers.getGroupName())) {
            peersUrl = peers.getServerAddress() + "/" + peers.getGroupName();
        }else {
            peersUrl = peers.getServerAddress();
        }
        peers.setCreateTime(new Date());
        String json = HttpUtil.get(peersUrl + Constant.API_STATUS);
        JSONObject jsonObject = JSONUtil.parseObj(json);
        if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
            Map<String, Object> res = indexService.getStatus(jsonObject.get("data"));
            String diskFreeSize = (String) res.get("diskFreeSize");
            double freeLength = FileSizeConverter.getLengthAutoCalToByte(diskFreeSize);
            peers.setDiskTotalSize(freeLength);
            peers.setDiskLeftSize(freeLength);
        }
        return this.save(peers);
    }

    @Override
    public boolean deletePeersById(int id) {
        return this.deletePeersById(id);
    }
}