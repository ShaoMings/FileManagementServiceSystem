package com.graduation.controller;


import com.graduation.model.vo.*;
import com.graduation.service.FileService;
import com.graduation.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.List;
import java.util.Map;


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

    /**
     * 重命名文件或文件夹
     *
     * @param path
     * @param oldName
     * @param newName
     * @return
     */
    @RequestMapping("rename")
    @ResponseBody
    public FileResponseVo renameFileOrFolder(String path, String oldName, String newName, String md5) {
        String oldPath = path + "/" + oldName;
        String newPath = path + "/" + newName;
        if (fileService.renameFileOrFolder(getPeersUrl(), oldPath, newPath, path, getPeersGroupName(), md5)) {
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
     * 通过path 删除文件
     *
     * @param path 文件路径
     * @return 文件响应对象
     */
    @RequestMapping("/deleteFile")
    @ResponseBody
    public FileResponseVo deleteFile(String path) {
        if (fileService.deleteFile(getPeersUrl(), getPeersGroupName(), path, false)) {
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
     * 图片转换接口
     *
     * @param path       文件路径 不含文件名
     * @param filename   文件名
     * @param srcSuffix  文件原本后缀
     * @param destSuffix 文件转换目标后缀
     * @return 响应对象
     */
    @RequestMapping("/picConverter")
    @ResponseBody
    public FileResponseVo convertPicture(String path, String filename, String srcSuffix, String destSuffix) {
        ConvertVo convertVo = new ConvertVo(getUser().getId(), path, filename, getPeersGroupName(),
                getPeersUrl(), srcSuffix, destSuffix);
        boolean isSuccess = fileService.convertPictureFile(convertVo);
        if (isSuccess) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("格式转换失败");
    }

    /**
     * 音频转换接口
     *
     * @param path       文件路径 不含文件名
     * @param filename   文件名
     * @param srcSuffix  文件原本后缀
     * @param destSuffix 文件转换目标后缀
     * @return 响应对象
     */
    @RequestMapping("/audioConverter")
    @ResponseBody
    public FileResponseVo convertAudio(String path, String filename, String srcSuffix, String destSuffix) {
        ConvertVo convertVo = new ConvertVo(getUser().getId(), path, filename, getPeersGroupName(),
                getPeersUrl(), srcSuffix, destSuffix);
        boolean isSuccess = fileService.convertAudioFile(convertVo);
        if (isSuccess) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("格式转换失败");
    }

    /**
     * 文档转换接口
     *
     * @param path       文件路径 不含文件名
     * @param filename   文件名
     * @param srcSuffix  文件原本后缀
     * @param destSuffix 文件转换目标后缀
     * @return 响应对象
     */
    @RequestMapping("/documentConverter")
    @ResponseBody
    public FileResponseVo convertDocument(String path, String filename, String srcSuffix, String destSuffix) {
        ConvertVo convertVo = new ConvertVo(getUser().getId(), path, filename, getPeersGroupName(),
                getPeersUrl(), srcSuffix, destSuffix);
        System.out.println(convertVo);
        boolean isSuccess = fileService.convertDocumentFile(convertVo);
        if (isSuccess) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("格式转换失败");
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
            URL url;
            String token;
            if ("".equals(path)) {
                token = TokenUtils.getAuthToken(AesUtils.getCheckCodeByDecryptStr(name));
            } else {
                token = TokenUtils.getAuthToken(AesUtils.getCheckCodeByDecryptStr(path + "/" + name));
            }
            url = new URL(getPeersUrl() + "/" + path + "/" + filename + "?auth_token=" + token);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(conn.getInputStream());
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

    /**
     * 文件分享 接口
     *
     * @param shareFileVo 分享信息封装类
     * @param request     请求对象
     * @return 响应对象
     * @throws Exception 异常
     */
    @RequestMapping("/share")
    @ResponseBody
    public FileResponseVo createShareFileLink(ShareFileVo shareFileVo, HttpServletRequest request) throws Exception {
        String untilToTime = DateConverter.dayCalculateBaseOnNow(shareFileVo.getDays());
        String content;
        if ("".equals(shareFileVo.getPath())) {
            content = "/" + getUser().getUsername() + "/" + shareFileVo.getFilename() + "@"
                    + untilToTime;
        } else {
            content = "/" + getUser().getUsername() + "/" + shareFileVo.getPath() + "/" + shareFileVo.getFilename() + "@"
                    + untilToTime;
        }
        String code = AesUtils.encrypt(content);
        String check = AesUtils.getCheckCodeByEncryptStr(code);
        String serverAddress = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        return FileResponseVo.success(new ShareFileLinkVo(serverAddress + "/s/download?code=" + code, check, untilToTime));
    }

    /**
     * 异源文件导入接口
     *
     * @param path 当前保存路径
     * @param link 异源链接
     * @return 响应对象
     */
    @RequestMapping("/import")
    @ResponseBody
    public FileResponseVo downloadFromLink(String path, String link) {
        if (StringUtils.isNotBlank(link)) {
            if (link.endsWith(".git")) {
                List<String> filesPath = GitUtils.pullFilesFromGit(link);
                List<FileResponseVo> list = FileUtils.uploadDir("git", path, getPeersUrl() + Constant.API_UPLOAD, getBackUrl(), filesPath);
                if (list == null) {
                    return FileResponseVo.fail("链接需要身份认证，请输入开源的git仓库链接!");
                }
                list.forEach(e -> {
                    UploadResultVo resultVo = (UploadResultVo) e.getData();
                    String filePath = resultVo.getPath();
                    fileService.saveFilePathByUserId(getUser().getId(), filePath, getPeers().getId());
                });
                String deletePath = Constant.OUTPUT_TMP_FILE_PATH + link.substring(link.lastIndexOf("/") + 1, link.lastIndexOf("."));
                File tmpPath = new File(deletePath);
                if (tmpPath.exists()) {
                    GitUtils.deleteDir(tmpPath);
                }
                return FileResponseVo.success();
            } else {
                URL url = null;
                try {
                    url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = conn.getInputStream();
                    String contentDisposition = new String(conn.getHeaderField("Content-Disposition").getBytes("utf-8"), "utf-8");
                    String filename = URLDecoder.decode(contentDisposition.substring(contentDisposition.indexOf("=") + 1).trim(), "UTF-8");
                    FileResponseVo responseVo = FileUtils.upload(inputStream, filename, path, "link",
                            getPeersUrl() + Constant.API_UPLOAD, getBackUrl());
                    UploadResultVo resultVo = (UploadResultVo) responseVo.getData();
                    String filePath = resultVo.getPath();
                    fileService.saveFilePathByUserId(getUser().getId(), filePath, getPeers().getId());
                    return responseVo;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return FileResponseVo.fail("导入失败!");
    }

}

