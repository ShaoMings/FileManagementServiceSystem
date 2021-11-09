package com.graduation.controller.adapter.local;

import cn.hutool.core.util.StrUtil;
import com.graduation.model.pojo.User;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.UploadParamVo;
import com.graduation.model.vo.UploadResultVo;
import com.graduation.service.FileService;
import com.graduation.service.UserService;
import com.graduation.utils.Constant;
import com.graduation.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.graduation.utils.FileUtils.upload;

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

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

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
     * @param param 前端入参文件
     * @return 响应对象
     */
    @RequestMapping("/upload/moreFileUpload")
    @ResponseBody
    public FileResponseVo fileUpload(UploadParamVo param){
        User user = getUser();
        if (param.getFile().isEmpty()) {
            return FileResponseVo.fail("请先选择要上传的文件");
        }
        if (StrUtil.isBlank(param.getScene())){
            return FileResponseVo.fail("请先选择上传场景");
        }
        String username = user.getUsername();
        if ("/files".equals(param.getPath())){
            param.setPath(username);
        }
        if ("".equals(param.getPath())){
            param.setPath(username);
        }
        if (!param.getPath().startsWith(username)){
            if (param.getPath().startsWith("/")){
                param.setPath(username + param.getPath());
            }else {
                param.setPath(username + "/" + param.getPath());
            }
        }
        if (StrUtil.isBlank(param.getShowUrl())){
            param.setShowUrl(getUploadShowUrl());
        }
        MultipartFile multipartFile = param.getFile();
        long fileSize = multipartFile.getSize();
        if (userService.userUploadFileToUpdateDiskSpace(getPeersUrl(),user.getId(),username,fileSize)) {
            // 文件夹处理
            String originalFilename = multipartFile.getOriginalFilename();
            assert originalFilename != null;
            if (originalFilename.endsWith(Constant.DIR_FLAG_CONSTANT)) {
                List<FileResponseVo> list = FileUtils.uploadDirZip(param, getPeersUrl() + Constant.API_UPLOAD);
                list.forEach(e ->{
                    UploadResultVo resultVo = (UploadResultVo) e.getData();
                    String filePath = resultVo.getPath();
                    fileService.saveFilePathByUserId(getUser().getId(),filePath,getPeers().getId(), resultVo.getMd5());
                });
                return FileResponseVo.success("上传文件夹成功!");
            }else {
                FileResponseVo responseVo = null;
                responseVo = upload(multipartFile,param.getPath(),
                        param.getScene(),getPeersUrl() + Constant.API_UPLOAD, param.getShowUrl());
                assert responseVo != null;
                UploadResultVo resultVo = (UploadResultVo) responseVo.getData();
                String filePath = resultVo.getPath();
                fileService.saveFilePathByUserId(getUser().getId(),filePath,getPeers().getId(), resultVo.getMd5());
                return responseVo;
            }
        }else {
            return FileResponseVo.fail("剩余存储空间不足!");
        }
    }
}
