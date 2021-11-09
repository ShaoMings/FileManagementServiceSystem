package com.graduation.controller.adapter.local;

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

    @RequestMapping("/checkError")
    public String checkError(){
        return "/error/checkError";
    }

    @RequestMapping("/outmoded")
    public String outmoded(){
        return "/error/outmoded";
    }
}
