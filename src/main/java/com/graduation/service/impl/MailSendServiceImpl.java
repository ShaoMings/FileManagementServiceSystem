package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.model.pojo.Mail;
import com.graduation.model.pojo.MailReceive;
import com.graduation.model.pojo.MailSend;
import com.graduation.mapper.MailSendMapper;
import com.graduation.model.pojo.User;
import com.graduation.model.vo.EmailSendVo;
import com.graduation.service.MailReceiveService;
import com.graduation.service.MailSendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.service.MailService;
import com.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shaoming
 * @since 2021-09-12
 */
@Service
public class MailSendServiceImpl extends ServiceImpl<MailSendMapper, MailSend> implements MailSendService {

    @Autowired
    private MailService mailService;

    @Autowired
    private MailReceiveService mailReceiveService;

    @Autowired
    private UserService userService;

    @Override
    public boolean saveMailSendRecord(EmailSendVo eMail) {
        QueryWrapper<Mail> mailQueryWrapper = new QueryWrapper<>();
        mailQueryWrapper.eq("mail_title", eMail.getTitle());
        mailQueryWrapper.eq("mail_content", eMail.getContent());
        int count = mailService.count(mailQueryWrapper);
        Mail mail = new Mail(null, eMail.getTitle(), eMail.getContent());
        if (count <= 0) {
            mailService.save(mail);
        }else {
            mail = mailService.getOne(mailQueryWrapper);
        }
        MailSend mailSend = new MailSend(null, eMail.getSendEmail(), eMail.getSendOutName(),
                new Date(), mail.getId(), 0, 1);
        boolean isSendSaved = this.save(mailSend);
        User receiveUser = userService.getUserByEmail(eMail.getSendEmail());
        if (receiveUser != null) {
            MailReceive mailReceive = new MailReceive(null, mail.getId(),eMail.getSendOutName(), receiveUser.getId(),new Date(), 0);
            boolean isReceiveSaved = mailReceiveService.save(mailReceive);
            return isSendSaved && isReceiveSaved;
        }
        return false;
    }
}
