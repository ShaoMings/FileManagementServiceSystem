package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 用户注册信息封装类
 *
 * @author shaoming
 * @date 2021/9/13 16:55
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignUpVo {
    private String name;
    private String email;
    private String account;
    private String password;
    private String confirm;
}
