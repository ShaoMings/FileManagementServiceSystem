package com.graduation.controller;

import cn.hutool.crypto.digest.MD5;
import com.graduation.exception.FileDownloadException;
import com.graduation.model.pojo.Peers;
import com.graduation.service.FileService;
import com.graduation.service.PeersService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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

    @Autowired
    PeersService peersService;
    @Autowired
    FileService fileService;

    private String peerAddress;

    @Value("${default.server.port}")
    private String port;

    @Value("${default.server.group}")
    private String group;

    @GetMapping("/download")
    public void downloadFileByLink(String code, HttpServletResponse response, HttpSession session) throws Exception {
        code = code.replaceAll(" ", "+");
        String path = AesUtils.decrypt(code);
        String groupFilePath = path.substring(path.indexOf("/", path.indexOf("/") + 1), path.lastIndexOf("@"));
        String untilToTime = path.substring(path.lastIndexOf("@") + 1);
        String md5 = path.substring(path.lastIndexOf("#")+1);
        String timestamp = System.currentTimeMillis() + "";
        timestamp = timestamp.substring(0,timestamp.length()-3);
        String token = MD5.create().digestHex(md5 + timestamp);
        if (session.getAttribute("isLogin") != null && (Boolean) session.getAttribute("isLogin")) {
            peerAddress = getPeersUrl();
        } else {
            // fileserver 与 web 同机器
//            peerAddress = "http://" + NetUtils.getLocalIpV4Address() + ":" + port + "/group1";
            Integer peerId = fileService.getFilePeerIdByFilePath("/" + group + groupFilePath);
            if (peerId!=null){
                Peers peers = peersService.getById(peerId);
                peerAddress = peers.getServerAddress()+"/"+group;
            }else {
                throw new FileDownloadException("文件服务地址有误!");
            }
        }
        // 链接检验未过期
        if (!DateConverter.isOverdueBaseNow(untilToTime)) {
            String name = groupFilePath.substring(groupFilePath.lastIndexOf("/") + 1);
            String tmpPath = groupFilePath.substring(groupFilePath.indexOf("/") + 1, groupFilePath.lastIndexOf("/"));
            String filename = URLEncoder.encode(name, "UTF-8");
            String filePath = URLEncoder.encode(tmpPath,"UTF-8");
            filename = StringUtils.replace(filename, "+", "%20");
            filePath = StringUtils.replace(filePath, "+", "%20");

            response.sendRedirect(peerAddress + "/" + filePath + "/" + filename+"?token="+token+"&timestamp="+timestamp);
//            BufferedInputStream in = null;
//            try {
//                // 将文件名 encode 确保 new URL 不出错
//                String filename = URLEncoder.encode(name, "UTF-8");
//                filename = StringUtils.replace(filename, "+", "%20");
//                URL url = new URL(peerAddress + "/" + tmpPath + "/" + filename);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//                conn.setConnectTimeout(20*1000);
//                conn.setReadTimeout(20*1000);
//                long length = conn.getContentLengthLong();
//                long from = 0,to = length -1;
//                in = new BufferedInputStream(conn.getInputStream());
//                response.reset();
//                // 将原本的文件名 encode为utf8后 空格被转为+了  要替换回原来的空格
//                name = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", " ");
//                String mimeType = request.getServletContext().getMimeType(name);
//                response.setContentType(null != mimeType ? mimeType : "application/octet-stream; charset=UTF-8");
//                response.setHeader("Content-Disposition", "attachment;filename=" + name);
//                // 断点续传
//                response.setHeader("Accept-Ranges", "bytes");
//                ServletOutputStream out = response.getOutputStream();
//                if (null == range) {
//                    response.setHeader("Content-Length", Long.toString(length));
//                } else {
//                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
//                    String[] ranges = range.replace("bytes=", "").split("-");
//                    if (ranges.length > 0) {
//                        from = Long.parseLong(ranges[0]);
//                    }
//                    if (ranges.length > 1) {
//                        to = Long.parseLong(ranges[1]);
//                    }
//                    // 设置本批次数据大小
//                    response.setHeader("Content-Length", Long.toString(to - from + 1L));
//                    // 设置本批次数据范围及数据总大小
//                    response.setHeader("Accept-Ranges", String.format("bytes %d-%d/%d", from, to, length));
//                }
//                in.skip(from);
//                // 将网络输入流转为输出流
//                // 限制数据流大小, 最大等于文件大小
//                long limit = to - from + 1;
//                // 缓冲大小
//                int bufferSize = (int) (limit > 2048 ? 2048 : limit);
//                // 创建缓冲数组
//                byte[] buffer = new byte[bufferSize];
//                int num = 0;
//                while (0 < limit && (num = in.read(buffer)) != -1) {
//                    out.write(buffer, 0, num);
//                    limit -= num;
//                    if (limit < bufferSize) {
//                        buffer = new byte[(int) limit];
//                    }
//                }
//                response.flushBuffer();
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            } finally {
//                if (in != null) {
//                    try {
//                        in.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
        } else {
           response.sendRedirect("/error/overdue");
        }
    }
}
