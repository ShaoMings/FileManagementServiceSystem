package com.graduation.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @RequestMapping("/username")
    public String getUsername(){
        return getUser().getUsername();
    }
}

