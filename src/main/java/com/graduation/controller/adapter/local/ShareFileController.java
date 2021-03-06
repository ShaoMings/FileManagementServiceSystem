package com.graduation.controller.adapter.local;

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

    @Value("${default.ipaddr}")
    private String address;

    @Value("${default.server.proxy}")
    private String proxy;

    @Value("${default.server.group}")
    private String group;


    @GetMapping("/download")
    public void downloadFileByLink(String code, String check, HttpServletResponse response, HttpSession session) throws Exception {
        code = code.replaceAll(" ", "+");
        String checkCode = AesUtils.getCheckCodeByEncryptStr(code);
        if (check.equals(checkCode)) {
            String path = AesUtils.decrypt(code);
            String groupFilePath = path.substring(path.indexOf("/", path.indexOf("/") + 1), path.lastIndexOf("@"));
            String username = path.substring(0, path.indexOf("/"));
            boolean isNoOverdue = redisUtils.hasKey("token-" + username + groupFilePath);
            if (isNoOverdue) {
                String untilToTime = path.substring(path.lastIndexOf("@") + 1);
                String token = AesUtils.getTokenByCode(code);
                Integer peerId = fileService.getFilePeerIdByFilePath("/" + group + "/" + username + groupFilePath);
                Peers peers = null;
                if (peerId != null) {
                    peers = peersService.getById(peerId);
                }
                if (!NetUtils.INTERNET_IP.equals(address)) {
                    if (session.getAttribute("isLogin") != null && (Boolean) session.getAttribute("isLogin")) {
                        peerAddress = getPeersUrl();
                    } else {
                        peerId = fileService.getFilePeerIdByFilePath("/" + group + "/" + username + groupFilePath);
                        if (peerId != null) {
                            peers = peersService.getById(peerId);
                        }
                        if (peers != null){
                            peerAddress = peers.getServerAddress() + "/" +  peers.getGroupName();
                        }
                    }
                } else {
                    peerAddress = proxy + "/" + (peers != null ? peers.getGroupName() : group);
                }
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
