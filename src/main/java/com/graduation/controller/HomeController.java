package com.graduation.controller;

import com.graduation.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class HomeController extends BaseController{

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/share")
    public String share(Model model) {
        model.addAttribute("role",userRoleService.getUserRole(getUser().getId()));
        return "share";
    }

    @RequestMapping("/space")
    public String space(){
        return "space";
    }

}
