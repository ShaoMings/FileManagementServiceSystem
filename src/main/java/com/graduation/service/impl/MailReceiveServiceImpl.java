package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.model.pojo.MailReceive;
import com.graduation.mapper.MailReceiveMapper;
import com.graduation.service.MailReceiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    public boolean deleteMailReceiveByMailId(Integer mailId) {
        return this.removeById(mailId);
    }

    @Override
    public boolean deleteMailReceivesByMailIds(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        return removeByIds(list);
    }

    @Override
    public boolean changeReadStatusById(Integer id) {
        MailReceive mailReceive = this.getById(id);
        mailReceive.setState(1);
        return this.updateById(mailReceive);
    }
}
