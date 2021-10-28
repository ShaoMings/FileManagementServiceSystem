package com.graduation.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.graduation.model.dto.gitee.response.ContentTreeDto;
import com.graduation.repo.adapter.GiteeAdapter;
import com.graduation.utils.FileSizeConverter;
import com.graduation.jcr.utils.JcrUtils;
import com.graduation.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/27 10:58
 * @since 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {


    @Autowired
    private JcrUtils jcrUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private GiteeAdapter giteeAdapter;


    @RequestMapping("/addDir")
    public String addDir(String path) throws IOException, RepositoryException {
        jcrUtils.addNodeOnRootByAbsPath(path);
        Node node = jcrUtils.getNodeByAbsPath(path);
        node.setProperty("test", "hello");
        boolean hasNode = jcrUtils.getNodeByAbsPath(path) != null;
        System.out.println(hasNode);
        return JSONUtil.toJsonStr(jcrUtils.getAllFilesPathInfo());
    }


    @RequestMapping("/addFile")
    public String upload(MultipartFile file, @RequestParam(name = "path", required = false) String path) throws IOException, RepositoryException {

        String filename = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        Node rootNode = jcrUtils.getRootNode();
        boolean isAdd;
        if (StringUtils.isNotBlank(path)) {
            isAdd = jcrUtils.addPropertyOnNodeByAbsPath(filename, inputStream, path);
        } else {
            isAdd = jcrUtils.addPropertyOnNode(filename, inputStream, rootNode);
        }
        return JSONUtil.toJsonStr(jcrUtils.getAllFilesPathInfo());
    }


    @RequestMapping("/addString")
    public String upload(String json, @RequestParam(name = "path", required = false) String path) throws IOException, RepositoryException {

        Node rootNode = jcrUtils.getRootNode();
        boolean isAdd;
        if (StringUtils.isNotBlank(path)) {
            isAdd = jcrUtils.addPropertyOnNodeByAbsPath("hello.txt", json, path);
        } else {
            isAdd = jcrUtils.addPropertyOnNode("hello.txt", json, rootNode);
        }
        return JSONUtil.toJsonStr(jcrUtils.getAllFilesPathInfo());
    }


    @RequestMapping("/query")
    public String query(String path, HttpServletResponse response) throws IOException, RepositoryException {
        InputStream inputStream = jcrUtils.getFileByAbsPath(path);
        byte[] bytes = new byte[1024];
        System.out.println(FileSizeConverter.getLength(inputStream.available()));
        ServletOutputStream out = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename=" + path.substring(path.lastIndexOf("/") + 1));
        int len = -1;
        while ((len = inputStream.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        out.flush();
        return JSONUtil.toJsonStr(jcrUtils.getAllFilesPathInfo());
    }

    @RequestMapping("/queryAllPaths")
    public String queryAllPaths() {
        return JSONUtil.toJsonStr(jcrUtils.getAllDirsPathInfo());
    }

    @RequestMapping("/queryAllFiles")
    public String queryAllFiles() {
        return JSONUtil.toJsonStr(jcrUtils.getAllFilesPathInfo());
    }

    @RequestMapping("/queryString")
    public String queryString(String path) throws IOException, RepositoryException {
        return JSONUtil.toJsonStr(jcrUtils.getStringPropertyByAbsPath(path));
    }

    @RequestMapping("/remove")
    public String remove(String path) throws IOException, RepositoryException {
        System.out.println(jcrUtils.removeItemByAbsPath(path));
        System.out.println(JSONUtil.toJsonStr(jcrUtils.getAllDirsPathInfo()));
        return JSONUtil.toJsonStr(jcrUtils.getAllFilesPathInfo());
    }

    @RequestMapping("/renameDir")
    public String renameDir(String oldPath, String newName) throws RepositoryException {
        System.out.println("old: " + JSONUtil.toJsonStr(jcrUtils.getAllDirsPathInfo()));
        System.out.println(jcrUtils.renameNodeByAbsPath(oldPath, newName));
        System.out.println("new: " + JSONUtil.toJsonStr(jcrUtils.getAllDirsPathInfo()));
        return JSONUtil.toJsonStr(jcrUtils.getAllFilesPathInfo());
    }

    @RequestMapping("/renameFile")
    public String renameDir(String dirPath, String oldName, String newName) throws RepositoryException {
        System.out.println("old: " + JSONUtil.toJsonStr(jcrUtils.getAllFilesPathInfo()));
        System.out.println(jcrUtils.renamePropertyByAbsPath(dirPath, oldName, newName));
        return JSONUtil.toJsonStr(jcrUtils.getAllFilesPathInfo());
    }


    @RequestMapping("/initTest")
    public String initTest() throws RepositoryException {
        String s = HttpUtil.get("https://gitee.com/api/v5/repos/shaoming123/JavaReview/git/trees/master?access_token=4e58b2c5adb230755c980dfed542654f&recursive=1");
        ContentTreeDto fileTreeDto = JSONUtil.toBean(s, ContentTreeDto.class);
        return JSONUtil.toJsonStr(fileTreeDto.getTree());
    }


    @RequestMapping("/init")
    public String init() throws RepositoryException {
        String api = "https://gitee.com/api/v5/repos/shaoming123/JavaReview/git/trees/master";
        Map<String, Object> params = new HashMap<>(2);
        params.put("access_token", "4e58b2c5adb230755c980dfed542654f");
        params.put("recursive", 1);
        giteeAdapter.initializer("shaoming/JavaReview",api, params, "GET");

        return JSONUtil.toJsonStr(jcrUtils.getAllFilesPathInfo());
    }

    @RequestMapping("/getTree")
    public String getTree(String path){
        return JSONUtil.toJsonStr(jcrUtils.getContentTreeOfNodeByAbsPath(path));
    }


}
