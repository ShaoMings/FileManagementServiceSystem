package com.graduation.controller.adapter.local;


import com.graduation.model.pojo.Share;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.OpenFileInfoVo;
import com.graduation.model.vo.UserOpenFileInfoVo;
import com.graduation.service.FileService;
import com.graduation.service.MailReceiveService;
import com.graduation.service.ShareService;
import com.graduation.service.UserRoleService;
import com.graduation.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private MailReceiveService mailReceiveService;

    @RequestMapping("/open")
    public FileResponseVo openFile(String filename, String path, String size) {
        String filePath;
        if ("".equals(path)) {
            filePath = "/" + getPeersGroupName() + "/" + getUser().getUsername() + "/" + filename;
        } else {
            filePath = "/" + getPeersGroupName() + "/" + getUser().getUsername() + "/" + path + "/" + filename;
        }
        Integer fileId = fileService.getFileIdByFilePath(filePath);
        Integer userRole = userRoleService.getUserRole(getUser().getId());
        if (path == ""){
            path = filePath;
        }
        boolean isShared = shareService.save(new Share(null, fileId,filename,path, size,getUser().getUsername(),
                getUser().getNickName(),userRole, new Date(), null, null,0,null));
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
        if ("".equals(path)) {
            filePath = "/" + getPeersGroupName() + "/" + getUser().getUsername() + "/" + filename;
        } else {
            filePath = "/" + getPeersGroupName() + "/" + getUser().getUsername() + "/" + path + "/" + filename;
        }
        Integer fileId = fileService.getFileIdByFilePath(filePath);
        boolean isPrivateRemove = shareService.privateFileToRemoveRecordByFilePath(filePath);
        boolean statusChanged = fileService.changeOpenStatusById(fileId, 0);
        if (isPrivateRemove && statusChanged) {
            return FileResponseVo.success();
        } else {
            return FileResponseVo.fail("私有失败!");
        }
    }

    @RequestMapping("/record")
    public FileResponseVo getOpenRecord() {
        LocalDateTime startDate = LocalDateTime.now().plusDays(-7);
        Date start = Date.from( startDate.atZone( ZoneId.systemDefault()).toInstant());
        List<Share> records = shareService.getSevenDayShareFilesRecord(start, new Date());
        List<OpenFileInfoVo> list = new ArrayList<>();
        records.forEach(r->list.add(new OpenFileInfoVo(r.getId(),r.getFileName(),r.getFilePath(),r.getSharerUsername(),r.getSharer(),
                r.getSharerRole(),r.getFileSize(),DateConverter.getFormatDate(r.getShareTime()),r.getDownloadCount(),
                r.getReadCount(),r.getRemote(),r.getToken())));
        return FileResponseVo.success(list);
    }

    @RequestMapping("/delRecord")
    public FileResponseVo deleteRecord(Integer shareId){
        Share share = shareService.getById(shareId);
        Integer fileId = share.getFileId();
        String sharerUsername = share.getSharerUsername();
        String fileName = share.getFileName();
        String shareTime = DateConverter.getFormatDate(share.getShareTime());
        if (shareService.removeById(shareId)) {
            if (fileId!=null){
                if (fileService.changeOpenStatusById(fileId,0)) {
                    // 删除的不是自己的分享
                    if (!sharerUsername.equals(getUser().getUsername())){
                        // 向分享用户发送删除信息
                        boolean isNoticed = mailReceiveService.addNoticeOfShareDeletedByUserName(sharerUsername,
                                getUser().getNickName(), fileName, shareTime);
                        if (isNoticed){
                            return FileResponseVo.success();
                        }
                    }else {
                        return FileResponseVo.success();
                    }
                }
            }else {
                if (!sharerUsername.equals(getUser().getUsername())){
                    // 向分享用户发送删除信息
                    boolean isNoticed = mailReceiveService.addNoticeOfShareDeletedByUserName(sharerUsername,
                            getUser().getNickName(), fileName, shareTime);
                    if (isNoticed){
                        return FileResponseVo.success();
                    }
                }else {
                    return FileResponseVo.success();
                }
            }

        }
        return FileResponseVo.fail("删除失败!");
    }


    @RequestMapping("/userRecord")
    public FileResponseVo getUserOpenRecord() {
        LocalDateTime startDate = LocalDateTime.now().plusDays(-7);
        Date start = Date.from( startDate.atZone( ZoneId.systemDefault()).toInstant());
        List<Share> records = shareService.getSevenDayShareFilesRecordByUserName(start, new Date(),getUser().getUsername());
        List<UserOpenFileInfoVo> list = new ArrayList<>();
        records.forEach(r->{
            String md5 = "";
            if (r.getFileId()!=null){
                md5 = fileService.getFileMd5ByFileId(r.getFileId());
            }
            list.add(new UserOpenFileInfoVo(r.getId(),r.getFileName(),r.getFilePath(),md5,r.getSharerUsername(),r.getSharer(),
                    r.getFileSize(),DateConverter.getFormatDate(r.getShareTime()),r.getDownloadCount(),r.getReadCount()));
        });
        return FileResponseVo.success(list);
    }


    @RequestMapping("/download")
    public FileResponseVo downloadCountAdd(String shareId,String newCount){
        boolean isAdd = shareService.downloadCountPlusById(Integer.parseInt(shareId), Integer.parseInt(newCount));
        if (isAdd){
            return FileResponseVo.success();
        }else {
            return FileResponseVo.fail("下载加1失败!");
        }
    }

    @RequestMapping("/read")
    public FileResponseVo readCountAdd(String shareId,String newCount){
        boolean isAdd = shareService.readCountPlusById(Integer.parseInt(shareId), Integer.parseInt(newCount));
        if (isAdd){
            return FileResponseVo.success();
        }else {
            return FileResponseVo.fail("浏览加1失败!");
        }
    }
}

