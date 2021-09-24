package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *  上传文件参数封装类
 * @author shaoming
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadParamVo {
    private MultipartFile file;
    private String scene;
    private String path;
    private String showUrl;
}
