package com.graduation.controller;

import com.graduation.utils.AesUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description 文件分享处理类
 *
 * @author shaoming
 * @date 2021/8/30 16:44
 * @since 1.0
 */
@Controller
@RequestMapping("/s")
public class ShareFileController {

    @GetMapping("/d/{code}")
    public String redirectDownloadLink(@PathVariable("code") String code) throws Exception {
                    String path = AesUtils.decrypt(code);
        //    /group1/test/files/2020-2021-02网络协议分析与编程实验题目.pdf
            String groupFilePath = path.substring(path.indexOf("/", path.indexOf("/") + 1),path.lastIndexOf("@"));
            String untilToTime = path.substring(path.lastIndexOf("@")+1);
            return "redirect:"+groupFilePath;
    }
}
