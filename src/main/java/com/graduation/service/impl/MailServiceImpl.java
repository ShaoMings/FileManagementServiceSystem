package com.graduation.service.impl;

import com.graduation.model.pojo.Mail;
import com.graduation.mapper.MailMapper;
import com.graduation.model.pojo.MailReceive;
import com.graduation.model.vo.EmailReceiveVo;
import com.graduation.model.vo.ListDataResponseVo;
import com.graduation.service.MailReceiveService;
import com.graduation.service.MailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail> implements MailService {

    @Autowired
    private MailReceiveService receiveService;

    @Override
    public ListDataResponseVo<EmailReceiveVo> getMailsByUserId(Integer userId) {
        List<EmailReceiveVo> list = new ArrayList<>();
        List<MailReceive> receiveList = receiveService.getMailReceivesByUserId(userId);
        receiveList.forEach(e-> list.add(new EmailReceiveVo(this.getById(e.getMailId()),e.getId(),e.getReceiveTime(),e.getState(),e.getSenderName())));
        return new ListDataResponseVo<>(0, "", list.size(), list);
    }

    @Override
    public String getMailContentByMailId(Integer mailId) {
        Mail mail = this.getById(mailId);
        return mail.getMailContent();
    }
}
