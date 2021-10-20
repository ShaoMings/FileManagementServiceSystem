package com.graduation.controller;

import cn.hutool.json.JSONUtil;
import com.graduation.utils.JcrUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/27 10:58
 * @since 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private JcrUtils jcrUtils;

    @RequestMapping("/upload")
    public String upload(MultipartFile file, @RequestParam(name = "path",required = false) String path) throws IOException, RepositoryException {

        String filename = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        Node rootNode = jcrUtils.getRootNode();
        boolean isAdd;
        if (StringUtils.isNotBlank(path)){
            isAdd = jcrUtils.addPropertyOnNodeByAbsPath(filename,inputStream,path);
        }else {
             isAdd = jcrUtils.addPropertyOnNode(filename, inputStream, rootNode);
        }
//        System.out.println("isAdd = " + isAdd);
        return JSONUtil.toJsonStr(jcrUtils.getAllPathsInfo());
    }


}
