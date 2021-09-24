package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 邮件发送信息封装类
 *
 * @author shaoming
 * @date 2021/9/12 16:39
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailSendVo {
    private String title;
    private String sendOutName;
    private String sendEmail;
    private String content;
}
