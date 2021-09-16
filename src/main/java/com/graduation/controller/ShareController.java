package com.graduation.controller;


import com.graduation.model.pojo.Share;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.service.FileService;
import com.graduation.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shaoming
 * @since 2021-09-16
 */
@RestController
@RequestMapping("/share")
public class ShareController extends BaseController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ShareService shareService;

    @RequestMapping("/open")
    public FileResponseVo openFile(String filename, String path) {
        String filePath;
        if ("".equals(path)){
            filePath = "/" + getPeersGroupName() + "/" + getUser().getUsername() + "/"  + filename;
        }else {
            filePath = "/" + getPeersGroupName() + "/" + getUser().getUsername() + "/" + path + "/" + filename;
        }
        Integer fileId = fileService.getFileIdByFilePath(filePath);
        boolean isShared = shareService.save(new Share(null, fileId, new Date(), null, null));
        boolean statusChanged = fileService.changeOpenStatusById(fileId, 1);
        if (isShared && statusChanged) {
            return FileResponseVo.success();
        } else {
            return FileResponseVo.fail("公开失败!");
        }
    }

    @RequestMapping("/private")
    public FileResponseVo privateFile(String filename, String path) {
        String filePath;
        if ("".equals(path)){
            filePath = "/" + getPeersGroupName() + "/" + getUser().getUsername() + "/"  + filename;
        }else {
            filePath = "/" + getPeersGroupName() + "/" + getUser().getUsername() + "/" + path + "/" + filename;
        }
        Integer fileId = fileService.getFileIdByFilePath(filePath);
        boolean isPrivateRemove = shareService.privateFileToRemoveRecordByFileId(fileId);
        boolean statusChanged = fileService.changeOpenStatusById(fileId, 0);
        if (isPrivateRemove && statusChanged) {
            return FileResponseVo.success();
        } else {
            return FileResponseVo.fail("私有失败!");
        }
    }
}

