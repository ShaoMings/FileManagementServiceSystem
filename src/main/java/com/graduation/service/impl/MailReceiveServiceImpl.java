package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.model.pojo.MailReceive;
import com.graduation.mapper.MailReceiveMapper;
import com.graduation.service.MailReceiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shaoming
 * @since 2021-09-12
 */
@Service
public class MailReceiveServiceImpl extends ServiceImpl<MailReceiveMapper, MailReceive> implements MailReceiveService {

    @Override
    public List<MailReceive> getMailReceivesByUserId(Integer userId) {
        QueryWrapper<MailReceive> receiveQueryWrapper = new QueryWrapper<>();
        receiveQueryWrapper.eq("receive_id",userId);
        return this.list(receiveQueryWrapper);
    }

    @Override
    public boolean deleteMailReceiveByMailId(Integer mailId, Integer userId) {
        QueryWrapper<MailReceive> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mail_id",mailId);
        queryWrapper.eq("receive_id",userId);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean deleteMailReceivesByMailIds(int[] ids,Integer userId) {
        QueryWrapper<MailReceive> queryWrapper = new QueryWrapper<>();
        boolean isAllDel = true;
        for (int id : ids) {
            queryWrapper.eq("mail_id",id);
            queryWrapper.eq("receive_id",userId);
            isAllDel = this.remove(queryWrapper);
            queryWrapper.clear();
        }
        return isAllDel;
    }

    @Override
    public boolean changeReadStatusById(Integer id) {
        MailReceive mailReceive = this.getById(id);
        mailReceive.setState(1);
        return this.updateById(mailReceive);
    }
}
