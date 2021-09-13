package com.graduation.service;

import com.graduation.model.pojo.MailSend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.model.vo.EmailSendVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shaoming
 * @since 2021-09-12
 */
public interface MailSendService extends IService<MailSend> {

    /**
     *  保存邮件发送记录
     * @param eMail 邮件封装类
     * @return 是否保存成功
     */
    boolean saveMailSendRecord(EmailSendVo eMail);
}
