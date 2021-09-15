package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 被管理的用户封装类
 *
 * @author shaoming
 * @date 2021/9/15 10:04
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManageUserVo {
    private Integer id;
    private String userName;
    private String nickName;
    private String password;
    private Integer gender;
    private Integer age;
    private String email;
    private Integer peersId;
    private Integer roleId;
}
