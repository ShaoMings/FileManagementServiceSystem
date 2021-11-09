package com.graduation.controller.adapter.remote.gitee;

import cn.hutool.core.codec.Base64;
import com.graduation.controller.adapter.local.BaseController;
import com.graduation.exception.FileDownloadException;
import com.graduation.model.pojo.Peers;
import com.graduation.model.pojo.Share;
import com.graduation.model.pojo.User;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.ShareFileLinkVo;
import com.graduation.model.vo.ShareFileVo;
import com.graduation.repo.adapter.GiteeAdapter;
import com.graduation.repo.adapter.TokenProxy;
import com.graduation.service.ShareService;
import com.graduation.service.UserRoleService;
import com.graduation.utils.AesUtils;
import com.graduation.utils.CommonUtils;
import com.graduation.utils.DateConverter;
import com.graduation.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description 仓库内容  文件内容控制器
 *
 * @author shaoming
 * @date 2021/10/16 15:02
 * @since 1.0
 */
@RestController
@RequestMapping("/repo/gite")
public class ContentBlobController extends BaseController {

    @Autowired
    private GiteeAdapter giteeAdapter;

    @Autowired
    private ShareService shareService;

    @Autowired
    private UserRoleService roleService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/blob")
    public void getBlobContent(String path, String repo, String code, HttpServletResponse response) throws IOException {
        if (StringUtils.isNotBlank(code)){
            code = TokenProxy.tokenDecode(code);
        }
        if (path.contains("/")){
            String filename = path.substring(path.lastIndexOf("/")+1);
            byte[] bytes = giteeAdapter.getRemoteFileContent(getUser().getUsername(), path, repo, code);
            if (bytes!=null){
                ServletOutputStream out = response.getOutputStream();
                filename = URLEncoder.encode(filename,"UTF-8").replaceAll("\\+","%20");
                response.setHeader("Content-Disposition", "attachment;filename="+filename);
                out.write(bytes);
                out.close();
            }
        }
    }

    @RequestMapping("/open/download")
    public void openFileDownload(String filePath,String token, HttpServletResponse response) throws IOException {
        if (shareService.existsShareByFilePath(filePath)) {
            token = TokenProxy.tokenDecode(token);
            String repo = CommonUtils.substringByCount(2,3,filePath,"/");
            byte[] bytes = giteeAdapter.getOpenFileContent(filePath,repo, token);
            String filename = filePath.substring(filePath.lastIndexOf("/")+1);
            if (bytes!=null){
                ServletOutputStream out = response.getOutputStream();
                filename = URLEncoder.encode(filename,"UTF-8").replaceAll("\\+","%20");
                response.setHeader("Content-Disposition", "attachment;filename="+filename);
                out.write(bytes);
                out.close();
            }
        }
    }

    @RequestMapping("/share")
    public FileResponseVo createShareFileLink(ShareFileVo shareFileVo,String token, HttpServletRequest request) throws Exception {
        String untilToTime = DateConverter.dayCalculateBaseOnNow(shareFileVo.getDays());
        shareFileVo.setPath(getUser().getUsername()+shareFileVo.getPath());
        String content = shareFileVo.getPath() + "@" + untilToTime;
        String filePath = shareFileVo.getPath();
        String code = AesUtils.encrypt(content);
        String check = AesUtils.getCheckCodeByEncryptStr(code);
        String serverAddress = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        long expires = DateConverter.getSecondsByDays(shareFileVo.getDays());
        redisUtils.set("token-" +  filePath, token, expires);
        return FileResponseVo.success(new ShareFileLinkVo(serverAddress + "/repo/gite/check/code?code=" + code, check, untilToTime));
    }


    @GetMapping("/download")
    public void downloadFileByLink(String code, String check, HttpServletResponse response) throws Exception {
        code = code.replaceAll(" ", "+");
        String checkCode = AesUtils.getCheckCodeByEncryptStr(code);
        if (check.equals(checkCode)) {
            String path = AesUtils.decrypt(code);
            String untilToTime = path.substring(path.lastIndexOf("@") + 1);
            String repo = CommonUtils.substringByCount(1,2,path,"/");
            path = path.substring(0,path.lastIndexOf("@"));
            boolean isNoOverdue = redisUtils.hasKey("token-" +path);
            if (isNoOverdue) {
                // 链接检验未过期
                if (!DateConverter.isOverdueBaseNow(untilToTime)) {
                    String token = (String) redisUtils.get("token-" +path);
                    token = TokenProxy.tokenDecode(token);
                    String filename = path.substring(path.lastIndexOf("/")+1);
                    String username = path.substring(0,path.indexOf("/"));
                    path = CommonUtils.substringByCount(path.startsWith("/")?3:2,path,"/");
                    byte[] bytes = giteeAdapter.getRemoteFileContent(username, path, repo, token);
                    if (bytes!=null){
                        ServletOutputStream out = response.getOutputStream();
                        filename = URLEncoder.encode(filename,"UTF-8").replaceAll("\\+","%20");
                        response.setHeader("Content-Disposition", "attachment;filename="+filename);
                        out.write(bytes);
                        out.close();
                    }
                } else {
                    response.sendRedirect("/error/overdue");
                }
            } else {
                response.sendRedirect("/error/outmoded");
            }
        } else {
            response.sendRedirect("/error/checkError");
        }
    }


    /**
     *  添加文件 传入的path 有 /
     * @param path 要添加的文件的文件路径 包含文件名
     * @param repo 仓库项目
     * @param token token
     * @return 响应对象
     */
    @RequestMapping("/addFile")
    public FileResponseVo addFile(MultipartFile file,String path, String repo, String token) throws IOException {
        if (StringUtils.isNotBlank(token)){
            token = TokenProxy.tokenDecode(token);
        }
        String filename = file.getOriginalFilename();
        String content = Base64.encode(file.getInputStream());
        Map<String,Object> params = new HashMap<>(8);
        String owner = giteeAdapter.getOwnerByToken(token);
        params.put("user",getUser().getUsername());
        params.put("repo",repo);
        params.put("path",path+"/"+filename);
        params.put("owner",owner);
        params.put("content",content);
        params.put("access_token",token);
        params.put("message","添加文件:"+filename+" "+ DateConverter.getNowMonthAndDay());
        String api = "https://gitee.com/api/v5/repos/"+owner+"/"+repo+"/contents/"+path+"/"+filename;
        boolean isAdded = giteeAdapter.addFile(api, params, "POST");
        return isAdded?FileResponseVo.success():FileResponseVo.fail("添加失败!");
    }

    /**
     *  删除文件 传入的path 有 /
     * @param path 要删除的文件路径 包含文件名
     * @param repo 仓库项目
     * @param token token
     * @return 响应对象
     */
    @RequestMapping("/removeFile")
    public FileResponseVo removeFile(String path, String repo, String token){
        if (StringUtils.isNotBlank(token)){
            token = TokenProxy.tokenDecode(token);
        }
        Map<String,Object> params = new HashMap<>(3);
        params.put("user",getUser().getUsername());
        params.put("repo",repo);
        params.put("token",token);
        boolean isRemoved = giteeAdapter.deleteFile(path, params);
        return isRemoved?FileResponseVo.success():FileResponseVo.fail("删除失败!");
    }

    @RequestMapping("/open")
    public FileResponseVo openFile(String filename,String path,String size,String repo,String token){
        User user = getUser();
        Integer userRole = roleService.getUserRole(user.getId());
        path = "/"+user.getUsername()+"/"+repo+path;
        if (!shareService.existsShareByFilePath(path)) {
            boolean isSaved = shareService.save(new Share(null, null, filename, path, size, user.getUsername(), user.getNickName(),
                    userRole, new Date(), null, null,1,token));
            boolean isModified = giteeAdapter.modifyFileFieldByFilePath(filename, path, "open", 1);
            if (isSaved && isModified){
                return FileResponseVo.success();
            }
        }
        return FileResponseVo.fail("公开失败!");
    }

    @RequestMapping("/private")
    public FileResponseVo privateFile(String filename,String path,String repo){
        User user = getUser();
        path = "/"+user.getUsername()+"/"+repo+path;
        if (shareService.existsShareByFilePath(path)){
            shareService.privateFileToRemoveRecordByFilePath(path);
        }
        boolean isModified = giteeAdapter.modifyFileFieldByFilePath(filename, path, "open", 0);
        return isModified?FileResponseVo.success():FileResponseVo.fail("私有失败!");
    }




}
