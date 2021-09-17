package com.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description 主页面控制器
 *
 * @author shaoming
 * @date 2021/9/16 15:00
 * @since 1.0
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/index")
    public String home() {
        return "share";
    }

}
