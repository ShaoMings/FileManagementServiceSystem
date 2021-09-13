package com.graduation.model.vo;

import com.graduation.model.pojo.Mail;
import com.graduation.utils.DateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description 收信箱信息封装类
 *
 * @author shaoming
 * @date 2021/9/12 19:38
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailReceiveVo {
    private Integer id;
    private Integer receiveMailId;
    private String sender;
    private String title;
    private String content;
    private String receivedDate;
    private Integer status;

    public EmailReceiveVo(Mail mail,Integer receiveMailId,Date receivedTime,Integer status,String sender){
        this.id = mail.getId();
        this.receiveMailId = receiveMailId;
        this.title = mail.getMailTitle();
        this.content = mail.getMailContent();
        this.status = status;
        this.sender = sender;
        this.receivedDate = DateConverter.getFormatDate(receivedTime,"yyyy-MM-dd HH:mm:ss");
    }
}
