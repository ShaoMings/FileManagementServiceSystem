package com.graduation.controller.adapter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/10/19 14:52
 * @since 1.0
 */
@Controller
@RequestMapping("/repo")
public class RepoIndexController {
    @RequestMapping("/index")
    public String index() {
        return "repo/file";
    }

    @RequestMapping("/upload")
    public String upload() {
        return "repo/upload";
    }
}
