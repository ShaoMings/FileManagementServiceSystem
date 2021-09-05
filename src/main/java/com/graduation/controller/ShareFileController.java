package com.graduation.controller;

import com.graduation.utils.AesUtils;
import com.graduation.utils.DateConverter;
import com.graduation.utils.FileUtils;
import com.graduation.utils.NetUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
    public void downloadFileByLink(String code,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        if (session.getAttribute("isLogin") != null && (Boolean) session.getAttribute("isLogin")) {
            peerAddress = getPeersUrl();
        } else {
            peerAddress = "http://" + NetUtils.getLocalIpV4Address() + ":" + port + "/group1";
        }
        peerAddress = "http://node-1:" + port + "/group1";
        code = code.replaceAll(" ", "+");
        String path = AesUtils.decrypt(code);
        String groupFilePath = path.substring(path.indexOf("/", path.indexOf("/") + 1), path.lastIndexOf("@"));
        String untilToTime = path.substring(path.lastIndexOf("@") + 1);
        if (!DateConverter.isOverdueBaseNow(untilToTime)) {
            String name = groupFilePath.substring(groupFilePath.lastIndexOf("/") + 1);
            String tmpPath = groupFilePath.substring(groupFilePath.indexOf("/") + 1, groupFilePath.lastIndexOf("/"));
            BufferedInputStream in = null;
            try {
                // 将文件名 encode 确保 new URL 不出错
                String filename = URLEncoder.encode(name, "UTF-8");
                filename = StringUtils.replace(filename, "+", "%20");
                request.getRequestDispatcher(peerAddress + "/" + tmpPath + "/" + filename).forward(request,response);
//                URL url = new URL(peerAddress + "/" + tmpPath + "/" + filename);
//                int contentLength = url.openConnection().getContentLength();
//                in = new BufferedInputStream(url.openStream());
//                response.reset();
//                // 将原本的文件名 encode为utf8后 空格被转为+了  要替换回原来的空格
//                name = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", " ");
//                response.setContentType("application/octet-stream");
//                response.setContentLength(contentLength);
//                response.setHeader("Content-Disposition", "attachment;filename=" + name);
//                // 将网络输入流转为输出流
//                int len;
//                while ((len = in.read()) != -1) {
//                    response.getOutputStream().write(len);
//                }
//                response.getOutputStream().close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
           response.sendRedirect("/error/overdue");
        }
    }
}
