package com.graduation.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author shaoming
 * @since 2021-09-12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MailSend对象", description="")
public class MailSend implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "默认主键")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "接收人email")
    private String receiveUserEmail;

    @ApiModelProperty(value = "发送人姓名")
    private String sendOutName;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "邮件id")
    private Integer mailId;

    @ApiModelProperty(value = "发送状态 0已发送 1已删除")
    private Integer sendState;

    @ApiModelProperty(value = "接收状态 0未接收  1已接收")
    private Integer receiveState;


}
