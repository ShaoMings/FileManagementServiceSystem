package com.graduation.controller;

import com.graduation.utils.AesUtils;
import com.graduation.utils.DateConverter;
import com.graduation.utils.FileUtils;
import com.graduation.utils.NetUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

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

    private String peerAddress;

    @Value("${default.server.port}")
    private String port;

    @GetMapping("/download")
    public void downloadFileByLink(String code, HttpServletResponse response, HttpSession session) throws Exception {
        if (session.getAttribute("isLogin")!=null && (Boolean) session.getAttribute("isLogin")){
            peerAddress = getPeersUrl();
        }else {
            peerAddress = NetUtils.getLocalIpV4Address()+":"+port+"/group1";
        }
        code = code.replaceAll(" ", "+");
        String path = AesUtils.decrypt(code);
        String groupFilePath = path.substring(path.indexOf("/", path.indexOf("/") + 1), path.lastIndexOf("@"));
        String untilToTime = path.substring(path.lastIndexOf("@") + 1);
        if (!DateConverter.isOverdueBaseNow(untilToTime)){
            String filename = groupFilePath.substring(groupFilePath.lastIndexOf("/")+1);
            String tmpPath = groupFilePath.substring(groupFilePath.indexOf("/")+1,groupFilePath.lastIndexOf("/"));
            InputStream inputStream = FileUtils.getFileDownloadStream(tmpPath,filename,peerAddress);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(filename.trim(), "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                bis = new BufferedInputStream(inputStream);
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            response.sendRedirect("/error/overdue");
        }
    }
}
