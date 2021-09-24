package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.model.pojo.Mail;
import com.graduation.model.pojo.MailReceive;
import com.graduation.mapper.MailReceiveMapper;
import com.graduation.service.MailReceiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.service.MailService;
import com.graduation.service.UserService;
import com.graduation.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
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

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

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

    @Override
    public boolean hasUnreadMessages(Integer userId) {
        QueryWrapper<MailReceive> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_id",userId);
        queryWrapper.eq("state",0);
        return this.list(queryWrapper).size()>0;
    }

    @Override
    public boolean addNoticeOfShareDeletedByUserName(String username,String admin,String fileName,String shareTime) {
        Mail mail = new Mail();
        mail.setMailTitle("文件被删除通知!");
        mail.setMailContent("您在 "+shareTime+" 分享的文件: "+fileName + "  于 "+ DateConverter.getFormatDate(new Date())+" 被管理员: "
        +admin + " 删除,请悉知!");
        boolean mailSaved = mailService.save(mail);
        if (mailSaved){
            Integer mailId = mail.getId();
            Integer receiverId = userService.getUserByUserName(username).getId();
            boolean isSaved = this.save(new MailReceive(null, mailId, admin, receiverId, new Date(), 0));
            if (isSaved){
                return true;
            }
        }
        return false;
    }
}
