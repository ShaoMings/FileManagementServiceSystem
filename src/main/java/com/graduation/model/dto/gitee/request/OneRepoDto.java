package com.graduation.model.dto.gitee.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 获取用户的某个仓库请求参数封装类
 *
 * @author shaoming
 * @date 2021/10/15 16:05
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OneRepoDto {
    /** 用户授权后的token 可缺省 */
    private String access_token;
    /** 用户名 */
    private String owner;
    /** 仓库名称 */
    private String repo;
}
