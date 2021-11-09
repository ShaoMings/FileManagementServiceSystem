package com.graduation.controller.adapter.local;

import com.graduation.utils.AesUtils;
import com.graduation.utils.TokenUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 前端预览文件 url 重写
 *
 * @author shaoming
 * @date 2021/9/8 13:58
 * @since 1.0
 */
@RestController
@RequestMapping("/preview")
public class PreviewController {

    @RequestMapping("/token")
    public String getFileUrlToken(String filePath){
        return  TokenUtils.getAuthToken(AesUtils.getCheckCodeByDecryptStr(filePath));
    }
}
