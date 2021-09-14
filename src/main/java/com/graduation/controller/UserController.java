package com.graduation.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @RequestMapping("/username")
    @ResponseBody
    public String getUsername(){
        return getUser().getUsername();
    }


    @RequestMapping("/manage")
    public String userManage(){
        return "manage";
    }



}

