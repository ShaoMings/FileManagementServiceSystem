package com.graduation.controller;

import com.graduation.exception.FileDownloadException;
import com.graduation.model.pojo.Peers;
import com.graduation.service.FileService;
import com.graduation.service.PeersService;
import com.graduation.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * Description 文件分享处理类
 *
 * @author shaoming
 * @date 2021/8/30 16:44
 * @since 1.0
 */
@Controller
@RequestMapping("/s")
public class ShareFileController extends BaseController {

    @Autowired
    private PeersService peersService;
    @Autowired
    private FileService fileService;

    @Autowired
    private RedisUtils redisUtils;

    private String peerAddress;

    @Value("${default.server.port}")
    private String port;

    @Value("${default.server.group}")
    private String group;


    @GetMapping("/download")
    public void downloadFileByLink(String code, String check, HttpServletResponse response, HttpSession session) throws Exception {
        code = code.replaceAll(" ", "+");
        String checkCode = AesUtils.getCheckCodeByEncryptStr(code);
        if (check.equals(checkCode)) {
            String path = AesUtils.decrypt(code);
            String groupFilePath = path.substring(path.indexOf("/", path.indexOf("/") + 1), path.lastIndexOf("@"));
            boolean isNoOverdue = redisUtils.hasKey("token-" + groupFilePath);
            if (isNoOverdue) {
                String untilToTime = path.substring(path.lastIndexOf("@") + 1);
                String username = path.substring(0, path.indexOf("/"));
                String token = AesUtils.getTokenByCode(code);
                if (session.getAttribute("isLogin") != null && (Boolean) session.getAttribute("isLogin")) {
                    peerAddress = getPeersUrl();
                } else {
                    // 非服务器部署 使用内网fileServer
                    Integer peerId = fileService.getFilePeerIdByFilePath("/" + group + "/" + username + groupFilePath);
                    if (peerId != null) {
                        Peers peers = peersService.getById(peerId);
                        peerAddress = peers.getServerAddress() + "/" + group;
                    } else {
                        throw new FileDownloadException("文件服务地址有误!");
                    }
                }

                // 服务器部署时打开
//        peerAddress = "http://1.15.221.117:8080/"+group;

                // 链接检验未过期
                if (!DateConverter.isOverdueBaseNow(untilToTime)) {
                    String name = groupFilePath.substring(groupFilePath.lastIndexOf("/") + 1);
                    int count = groupFilePath.length() - groupFilePath.replaceAll("/", "").length();
                    String filename;
                    if (count <= 1) {
                        filename = URLEncoder.encode(name, "UTF-8");
                        filename = StringUtils.replace(filename, "+", "%20");
                        response.sendRedirect(peerAddress + "/" + username + "/" + filename + "?auth_token=" + token);
                    } else {
                        String tmpPath = groupFilePath.substring(groupFilePath.indexOf("/") + 1, groupFilePath.lastIndexOf("/"));
                        filename = URLEncoder.encode(name, "UTF-8");
                        String filePath = URLEncoder.encode(tmpPath, "UTF-8");
                        filename = StringUtils.replace(filename, "+", "%20");
                        filePath = StringUtils.replace(filePath, "+", "%20");
                        response.sendRedirect(peerAddress + "/" + username + "/" + filePath + "/" + filename + "?auth_token=" + token);
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
}
