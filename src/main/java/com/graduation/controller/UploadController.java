package com.graduation.controller;

import cn.hutool.core.util.StrUtil;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.UploadResultVo;
import com.graduation.service.FileService;
import com.graduation.utils.Constant;
import com.graduation.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description 文件上传控制器
 *
 * @author shaoming
 * @date 2021/8/17 21:55
 * @since 1.0
 */
@Controller
@RequestMapping("/file")
public class UploadController extends BaseController{

    @Value("${upload.temp.path}")
    private String tempPath;

    @Autowired
    private FileService fileService;

    /**
     * 文件上传页面
     * @param model model对象
     * @return 页面
     */
    @RequestMapping("/upload")
    public String index(Model model){
        model.addAttribute("showAddress",getUploadShowUrl());
        return "file/upload";
    }

    /**
     * 文件上传
     * @param file 前端入参文件
     * @param scene 场景
     * @param serverAddress 服务地址 不含组名与api
     * @return 响应对象
     */
    @RequestMapping("/upload/moreFileUpload")
    @ResponseBody
    public FileResponseVo fileUpload(@RequestParam("file")MultipartFile file,String scene,String path,@RequestParam("showUrl") String serverAddress){
        if (file.isEmpty()) {
            return FileResponseVo.fail("请先选择要上传的文件");
        }
        if (StrUtil.isBlank(scene)){
            return FileResponseVo.fail("请先选择上传场景");
        }
        if (StrUtil.isBlank(serverAddress)){
            serverAddress = getUploadShowUrl();
        }

        // 文件夹处理
        String originalFilename = file.getOriginalFilename();
        if (originalFilename.endsWith(Constant.DIR_FLAG_CONSTANT)) {

        }

        FileResponseVo responseVo = FileUtils.upload(file, path, scene, getPeersUrl() + Constant.API_UPLOAD, serverAddress);
        UploadResultVo resultVo = (UploadResultVo) responseVo.getData();
        String filePath = resultVo.getPath();
        fileService.saveFilePathByUserId(getUser().getId(),filePath);
        return responseVo;
    }


}
