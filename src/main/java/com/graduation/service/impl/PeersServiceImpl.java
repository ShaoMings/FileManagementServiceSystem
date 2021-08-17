package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.model.dto.PageInfoDto;
import com.graduation.model.pojo.Peers;
import com.graduation.mapper.PeersMapper;
import com.graduation.service.PeersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
