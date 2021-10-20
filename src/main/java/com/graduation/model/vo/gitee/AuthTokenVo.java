package com.graduation.model.vo.gitee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 请求获取token返回的参数封装类
 *
 * @author shaoming
 * @date 2021/10/18 17:06
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthTokenVo {
    private String accessToken;
    private String tokenType;
    private String expiresIn;
    private String refreshToken;
    private String scope;
    private String createdAt;
}
