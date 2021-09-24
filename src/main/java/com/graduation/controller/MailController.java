package com.graduation.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shaoming
 * @since 2021-09-12
 */
@Controller
@RequestMapping("/mail")
public class MailController {

    @RequestMapping("/index")
    public String mailIndex() {
        return "mail";
    }

    @RequestMapping("/inboxes")
    public String mailInboxes() {
        return "inboxes";
    }

    @RequestMapping("/sendboxes")
    public String mailSendBoxes() {
        return "sendboxes";
    }

    @RequestMapping("/read")
    public String mailRead() {
        return "readMail";
    }

}

