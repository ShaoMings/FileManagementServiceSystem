package com.graduation.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="MailReceive对象", description="")
public class MailReceive implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "邮件id")
    private Integer mailId;

    @ApiModelProperty(value = "发信人姓名")
    private String senderName;

    @ApiModelProperty(value = "接收人id")
    private Integer receiveId;

    @ApiModelProperty(value = "接收时间")
    private Date receiveTime;

    @ApiModelProperty(value = "状态 0未读 1已读")
    private Integer state;


}
