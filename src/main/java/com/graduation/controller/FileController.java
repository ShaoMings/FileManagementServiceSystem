package com.graduation.controller;


import com.graduation.model.vo.FileResponseVo;
import com.graduation.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
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
    @RequestMapping("")
    public String index() {
        return "file/file";
    }

    /**
     * 获取一级目录
     *
     * @return 一级目录
     */
    @RequestMapping("/getParentFile")
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
    @RequestMapping("/getDirFile")
    @ResponseBody
    public FileResponseVo getDirFile(String dir) {
        return FileResponseVo.success(fileService.getDirFile(getBackUrl(), getPeersUrl(), dir));
    }

    @RequestMapping("rename")
    @ResponseBody
    public FileResponseVo renameFileOrFolder(String path, String oldName, String newName) {
        String oldPath = path + "/" + oldName;
        String newPath = path + "/" + newName;
        if (fileService.renameFileOrFolder(getPeersUrl(), oldPath, newPath,path,getPeersGroupName())) {
            return FileResponseVo.success();
        } else {
            return FileResponseVo.fail("重命名失败");
        }
    }

    /**
     * 通过关键字获取相关文件
     *
     * @param keywords 文件名关键字
     * @return 文件响应对象
     */
    @RequestMapping("/getSearchFiles")
    @ResponseBody
    public FileResponseVo getSearchFiles(String keywords) {
        return FileResponseVo.success(fileService.getFileInfoListByFileKeyword(getPeersUrl(), keywords));
    }


    /**
     * 通过md5 删除文件
     *
     * @param md5 文件md5
     * @return 文件响应对象
     */
    @RequestMapping("/deleteFile")
    @ResponseBody
    public FileResponseVo deleteFile(String md5) {
        if (fileService.deleteFile(getPeersUrl(), md5)) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("删除文件失败");
    }

    /**
     * 删除文件夹
     *
     * @param path 文件夹路径
     * @return 是否删除成功
     */
    @RequestMapping("/deleteDir")
    @ResponseBody
    public FileResponseVo deleteDir(String path) {
        if (fileService.deleteDir(getPeersUrl(), path)) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("删除文件夹失败");
    }

    /**
     * 创建文件夹
     *
     * @param path 文件夹路径
     * @return 响应对象
     */
    @RequestMapping("/mkdir")
    @ResponseBody
    public FileResponseVo mkdir(String path) {
        if (fileService.mkdir(getPeersUrl(), path)) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("创建文件夹失败");
    }


    /**
     * 通过md5获取文件详细信息
     *
     * @param md5 md5
     * @return 文件响应对象
     */
    @RequestMapping("/details")
    @ResponseBody
    public FileResponseVo details(String md5) {
        return fileService.getFileDetails(getPeersUrl(), md5);
    }


    /**
     * 文件下载
     *
     * @param path     路径
     * @param name     文件名
     * @param response http响应对象
     */
    @RequestMapping("/downloadFile")
    @ResponseBody
    public void downloadFile(String path, String name, HttpServletResponse response) {
        BufferedInputStream in = null;
        try {
            // 将文件名 encode 确保 new URL 不出错
            String filename = URLEncoder.encode(name, "UTF-8");
            filename = StringUtils.replace(filename, "+", "%20");
            response.setHeader("Content-Disposition", "attachment;filename=" + name);
            response.setContentType("application/octet-stream");
            URL url = new URL(getPeersUrl() + "/" + path + "/" + filename);
            in = new BufferedInputStream(url.openStream());
            response.reset();
            // 将原本的文件名 encode为utf8后 空格被转为+了  要替换回原来的空格
            name = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", " ");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + name);
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

