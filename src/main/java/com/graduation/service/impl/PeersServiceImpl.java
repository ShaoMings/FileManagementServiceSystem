package com.graduation.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.model.dto.PageInfoDto;
import com.graduation.model.pojo.Peers;
import com.graduation.mapper.PeersMapper;
import com.graduation.model.vo.ApiResultVo;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.service.IndexService;
import com.graduation.service.PeersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.utils.Constant;
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
        PageHelper.startPage(page, limit);
        List<Peers> allPeers = this.list();
        PageInfo<Peers> pageInfo = new PageInfo<>(allPeers);
        result.setTotal(pageInfo.getTotal());
        result.setState(200);
        result.setData(pageInfo.getList());
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
    public boolean checkPeersExist(String serverAddress) {
        QueryWrapper<Peers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_address", serverAddress);
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean addPeers(Peers peers) {
        peers.setCreateTime(new Date());
        return this.save(peers);
    }

    @Override
    public boolean deletePeersById(int id) {
        return this.deletePeersById(id);
    }
}
