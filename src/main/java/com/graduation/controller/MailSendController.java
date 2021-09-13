package com.graduation.controller;


import com.graduation.model.vo.EmailSendVo;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shaoming
 * @since 2021-09-12
 */
@RestController
@RequestMapping("/mail-send")
public class MailSendController {

    @Autowired
    private MailSendService mailSendService;

    @RequestMapping("/send")
    public FileResponseVo sendMail(EmailSendVo email){
        boolean isSent = mailSendService.saveMailSendRecord(email);
        if (isSent){
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("发送邮件失败,请检查邮箱是否有误!");
    }

}

