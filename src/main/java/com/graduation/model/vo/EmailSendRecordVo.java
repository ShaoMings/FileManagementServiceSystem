package com.graduation.model.vo;

import com.graduation.model.pojo.MailSend;
import com.graduation.utils.DateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/13 13:35
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailSendRecordVo {
    private Integer id;
    private String receiveUserEmail;
    private String content;
    private String sendTime;
    private Integer sendStatus;

    public EmailSendRecordVo(MailSend mailSend,String mailContent){
        this.id = mailSend.getId();
        this.receiveUserEmail = mailSend.getReceiveUserEmail();
        this.content = mailContent;
        this.sendTime = DateConverter.getFormatDate(mailSend.getSendTime(),"yyyy-MM-dd HH:mm:ss");
        this.sendStatus = mailSend.getSendState();
    }
}
