package com.graduation.model.vo;

import lombok.Data;

/**
 * Description 用于修改用户信息的封装类
 *
 * @author shaoming
 * @date 2021/8/18 11:22
 * @since 1.0
 */
@Data
public class ModifyUserVo {
    private Integer id;
    private String account;
    private String name;
    private Integer gender;
    private String email;
    private String newPassword;
    private String oldPassword;
}
