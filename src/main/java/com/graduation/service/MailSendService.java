package com.graduation.service;

import com.graduation.model.pojo.MailSend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.model.vo.EmailSendVo;
import com.graduation.model.vo.SendResponseVo;
import io.swagger.models.auth.In;

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

    /**
     * 删除邮件发送记录
     * @param id 邮件发送记录id
     * @return 是否删除成功
     */
    boolean deleteMailSendRecord(Integer id);

    /**
     * 删除选中的邮件发送记录
     * @param ids 邮件发送记录id
     * @return 是否删除成功
     */
    boolean deleteSelectMailSendRecord(Integer[] ids);

    /**
     * 通过用户名获取用户发送信件的记录
     * @param username 用户名
     * @return 记录封装类
     */
    SendResponseVo getUserSendMailRecord(String username);

}
