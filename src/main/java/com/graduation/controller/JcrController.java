package com.graduation.controller;

import com.graduation.model.vo.FileResponseVo;
import com.graduation.utils.JcrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 用于java 内容仓库管理的控制器
 *
 * @author shaoming
 * @date 2021/10/20 10:01
 * @since 1.0
 */
@RestController
@RequestMapping("/jcr")
public class JcrController {

    @Autowired
    private JcrUtils jcrUtils;

    @RequestMapping("/all/{repo}/{project}")
    public FileResponseVo getAllFilesOfRepo(@PathVariable("repo") String repo,@PathVariable("project") String project){
        jcrUtils.getAllPathsInfo();
        return FileResponseVo.fail("error");
    }
}
