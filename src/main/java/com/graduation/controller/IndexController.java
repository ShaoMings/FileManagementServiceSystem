package com.graduation.controller;

import com.graduation.model.pojo.User;
import com.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Description index
 *
 * @author shaoming
 * @date 2021/8/17 16:15
 * @since 1.0
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    UserService userService;

    /**
     * 首页视图控制
     * @param model 模型对象
     * @return 首页
     */
    @GetMapping("/")
    public String index(Model model){
        User user = getUser();
        user.setLastLoginTime(new Date());
        userService.updateById(user);
        model.addAttribute("user",user);
        return "index";
    }


}
