package com.graduation.controller;

import com.graduation.utils.AesUtils;
import com.graduation.utils.DateConverter;
import com.graduation.utils.FileUtils;
import com.graduation.utils.NetUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.net.URL;
import java.net.URLConnection;
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

    private String peerAddress;

    @Value("${default.server.port}")
    private String port;

    @GetMapping("/download")
    public void downloadFileByLink(String code, HttpServletResponse response, HttpSession session) throws Exception {
        if (session.getAttribute("isLogin") != null && (Boolean) session.getAttribute("isLogin")) {
            peerAddress = getPeersUrl();
        } else {
            peerAddress = "http://" + NetUtils.getLocalIpV4Address() + ":" + port + "/group1";
        }
        code = code.replaceAll(" ", "+");
        String path = AesUtils.decrypt(code);
        String groupFilePath = path.substring(path.indexOf("/", path.indexOf("/") + 1), path.lastIndexOf("@"));
        String untilToTime = path.substring(path.lastIndexOf("@") + 1);
        if (!DateConverter.isOverdueBaseNow(untilToTime)) {
            String filename = groupFilePath.substring(groupFilePath.lastIndexOf("/") + 1);
            String tmpPath = groupFilePath.substring(groupFilePath.indexOf("/") + 1, groupFilePath.lastIndexOf("/"));
            filename = URLEncoder.encode(filename, "UTF-8");
            filename = StringUtils.replace(filename, "+", "%20");
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                URL url = new URL(peerAddress + "/" + tmpPath + "/" + filename);
                URLConnection conn = url.openConnection();
                conn.setReadTimeout(20 * 1000);
                conn.setConnectTimeout(20 * 1000);
                final ByteArrayOutputStream output = new ByteArrayOutputStream();
                IOUtils.copy(conn.getInputStream(), output);
                response.reset(); // 非常重要
                response.setHeader("Content-Disposition", "attachment; filename=" + filename);
                response.setContentType("application/x-download");
                response.setContentLength(conn.getContentLength());
                byte[] buff = new byte[1024];
                os = response.getOutputStream();
                bis = new BufferedInputStream(new ByteArrayInputStream(output.toByteArray()));
                int i = 0;
                while ((i = bis.read(buff)) != -1) {
                    os.write(buff, 0, i);
                    os.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert bis != null;
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            response.sendRedirect("/error/overdue");
        }
    }
}
