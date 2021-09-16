package com.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description 提取码验证
 *
 * @author shaoming
 * @date 2021/9/16 12:04
 * @since 1.0
 */
@Controller
@RequestMapping("/check")
public class CheckController {

    @RequestMapping("/code")
    public String checkCode(String code, Model model){
        model.addAttribute("code",code);
        return "check";
    }
}
