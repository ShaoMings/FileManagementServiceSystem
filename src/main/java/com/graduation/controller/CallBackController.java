package com.graduation.controller;

import cn.hutool.json.JSONUtil;
import com.graduation.model.vo.BigFileInfoVo;
import com.graduation.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 用于大文件上传的回调控制
 *
 * @author shaoming
 * @date 2021/9/19 下午12:13
 * @since 1.0
 */
@RestController
@RequestMapping("/callback")
public class CallBackController extends BaseController{

    @Autowired
    private FileService fileService;

    @RequestMapping("/bigFileInfo")
    public void setBigFileInfo(@RequestParam("info")String info,@RequestParam("group") String peersGroupName){
        BigFileInfoVo fileInfoVo = JSONUtil.toBean(info, BigFileInfoVo.class);
        String saveFilePath = fileInfoVo.getPath().replace("files/","/"+peersGroupName+"/")
                +fileInfoVo.getName();
        fileService.saveBigFileMd5ByFilePath(fileInfoVo.getMd5(),saveFilePath);
    }
}
