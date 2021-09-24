package com.graduation.model.vo;

import com.graduation.model.pojo.MailSend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/13 13:16
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendResponseVo {
    private Integer code;
    private String msg;
    private Integer count;
    private List<EmailSendRecordVo> data;
}
