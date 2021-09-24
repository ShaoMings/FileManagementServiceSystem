package com.graduation.controller;


import com.graduation.model.vo.EmailSendVo;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.SendResponseVo;
import com.graduation.service.MailSendService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
public class MailSendController extends BaseController{

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

    @RequestMapping("/record")
    public SendResponseVo getSendRecord(){
        return mailSendService.getUserSendMailRecord(getUser().getUsername());
    }

    @RequestMapping("/delete")
    public FileResponseVo deleteSendRecord(Integer id){
        boolean isDel = mailSendService.deleteMailSendRecord(id);
        if (isDel){
            return FileResponseVo.success();
        }else {
            return FileResponseVo.fail("删除失败!");
        }
    }

    @RequestMapping("/deleteSelected")
    public FileResponseVo deleteSelectedMailSendRecord(@RequestParam("ids[]") String[] ids){
        Integer[] mailIds = (Integer[]) ConvertUtils.convert(ids, Integer.class);
        boolean isAllDel = mailSendService.deleteSelectMailSendRecord(mailIds);
        if (isAllDel){
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("删除失败!");
    }

}

