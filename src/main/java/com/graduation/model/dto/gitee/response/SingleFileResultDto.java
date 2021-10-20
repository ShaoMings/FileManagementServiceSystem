package com.graduation.model.dto.gitee.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 通过获取目录树返回的数据中 单个文件的api回调数据封装类
 *
 * @author shaoming
 * @date 2021/10/15 15:32
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SingleFileResultDto {
    /** 分支名(如master)、Commit或者目录Tree的SHA值 */
    private String sha;
    /** 文件大小 */
    private Integer size;
    /** 访问本文件的API */
    private String url;
    /** 或为base64 加密后的内容 */
    private String content;
    /** 加密编码格式 */
    private String encoding;


}
