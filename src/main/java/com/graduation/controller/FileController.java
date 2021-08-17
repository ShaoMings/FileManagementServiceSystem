package com.graduation.controller;


import com.graduation.model.vo.FileResponseVo;
import com.graduation.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

    /**
     * 文件列表页面
     *
     * @return 页面
     */
    @GetMapping("/")
    public String index() {
        return "file/file";
    }

    /**
     * 获取一级目录
     *
     * @return 一级目录
     */
    @GetMapping("/getParentFile")
    @ResponseBody
    public FileResponseVo getParentFile(String dir) {
        return FileResponseVo.success(fileService.getParentFile(getPeersGroupName(), getPeersUrl()));
    }

    /**
     * 获取指定目录文件
     *
     * @param dir 指定目录
     * @return 文件响应对象
     */
    @GetMapping("/getDirFile")
    @ResponseBody
    public FileResponseVo getDirFile(String dir) {
        return FileResponseVo.success(fileService.getDirFile(getBackUrl(), getPeersUrl(), dir));
    }


    /**
     * 通过md5 删除文件
     *
     * @param md5 文件md5
     * @return 文件响应对象
     */
    @GetMapping("/deleteFile")
    @ResponseBody
    public FileResponseVo deleteFile(String md5) {
        if (fileService.deleteFile(getPeersUrl(), md5)) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("删除文件失败");
    }

    /**
     * 通过md5获取文件详细信息
     *
     * @param md5 md5
     * @return 文件响应对象
     */
    @GetMapping("/details")
    @ResponseBody
    public FileResponseVo details(String md5) {
        return fileService.getFileDetails(getPeersUrl(), md5);
    }


    /**
     * 文件下载
     * @param path 路径
     * @param name 文件名
     * @param response http响应对象
     */
    @GetMapping("/downloadFile")
    @ResponseBody
    public void downloadFile(String path, String name, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        response.setContentType("application/octet-stream");
        BufferedInputStream in = null;
        try {
            URL url = new URL(getPeersUrl() + "/" + path + "/" + name);
            in = new BufferedInputStream(url.openStream());
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            // 将网络输入流转为输出流
            int len;
            while ((len = in.read()) != -1) {
                response.getOutputStream().write(len);
            }
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

