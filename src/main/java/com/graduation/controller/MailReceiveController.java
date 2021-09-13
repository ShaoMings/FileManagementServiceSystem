package com.graduation.controller;


import com.graduation.model.pojo.Mail;
import com.graduation.model.vo.EmailReceiveVo;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.ReceiveResponseVo;
import com.graduation.service.MailReceiveService;
import com.graduation.service.MailService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shaoming
 * @since 2021-09-12
 */
@RestController
@RequestMapping("/mail-receive")
public class MailReceiveController extends BaseController{

    @Autowired
    MailService mailService;

    @Autowired
    MailReceiveService receiveService;

    @RequestMapping("/mails")
    public ReceiveResponseVo getMailList(Integer userId){
        return mailService.getMailsByUserId(userId);
    }

    @RequestMapping("/delete")
    public FileResponseVo deleteMail(Integer mailId){
        boolean isDel = receiveService.deleteMailReceiveByMailId(mailId);
        if (isDel){
            return FileResponseVo.success();
        }else {
            return FileResponseVo.fail("删除失败!");
        }
    }

    @RequestMapping("/deleteSelected")
    public FileResponseVo deleteSelectedMail(@RequestParam(value = "ids[]") String[] ids){
        Integer[] mailIds = (Integer[]) ConvertUtils.convert(ids, Integer.class);
        boolean isAllDel = receiveService.deleteMailReceivesByMailIds(mailIds);
        if (isAllDel){
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("删除失败!");
    }


    @RequestMapping("/read")
    public FileResponseVo readMail(String id){
        boolean isChange = receiveService.changeReadStatusById(Integer.parseInt(id));
        if (isChange){
            return FileResponseVo.success();
        }else {
            return FileResponseVo.fail("阅读失败!");
        }
    }

}

