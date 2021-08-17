package com.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description index
 *
 * @author shaoming
 * @date 2021/8/17 16:15
 * @since 1.0
 */
@Controller
public class IndexController extends BaseController{

    /**
     * 首页视图控制
     * @param model 模型对象
     * @return 首页
     */
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("user",getUser());
        return "index";
    }

}
