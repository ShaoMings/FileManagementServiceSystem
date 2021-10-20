package com.graduation.model.dto.gitee.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 获取用户仓库文件内容请求参数封装类
 *
 * @author shaoming
 * @date 2021/10/16 15:04
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentBlobDto {
    /** 用户授权后的token 可缺省 */
    private String access_token;
    /** 用户名 */
    private String owner;
    /** 仓库名称 */
    private String repo;
    /** 可以是分支名(如master)、Commit或者目录Tree的SHA值*/
    private String sha;
    /** 文件名 */
    private String filename;
}
