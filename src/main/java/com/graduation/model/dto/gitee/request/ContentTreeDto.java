package com.graduation.model.dto.gitee.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 获取用户仓库目录树请求参数封装类
 *
 * @author shaoming
 * @date 2021/10/15 16:09
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentTreeDto {
    /** 用户授权后的token 可缺省 */
    private String access_token;
    /** 点击的文件夹路径  可缺省 */
    private String path;
    /** 用户名 */
    private String owner;
    /** 仓库名称 */
    private String repo;
    /** 可以是分支名(如master)、Commit或者目录Tree的SHA值*/
    private String sha;
    /** 文件类型 blob 表示为文件  tree表示为文件夹*/
    private String type;

}
