package com.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shaoming
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/overdue")
    public String overdue(){
        return "/error/overdue";
    }
}
