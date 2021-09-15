package com.graduation.service;

import com.graduation.model.pojo.Mail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.model.vo.TableResponseVo;



/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shaoming
 * @since 2021-09-12
 */
public interface MailService extends IService<Mail> {

    /**
     * 通过用户id获取信件信息
     * @param userId 用户id
     * @return 邮件信息
     */
    TableResponseVo getMailsByUserId(Integer userId);

    /**
     * 通过邮件id获取邮件内容
     * @param mailId 邮件id
     * @return 邮件内容
     */
    String getMailContentByMailId(Integer mailId);
}
