package com.graduation.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * @since 2021-09-16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Share对象", description="")
public class Share implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "默认主键")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件id")
    private Integer fileId;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件大小")
    private String fileSize;

    @ApiModelProperty(value = "分享人用户名")
    private String sharerUsername;

    @ApiModelProperty(value = "分享人")
    private String sharer;

    @ApiModelProperty(value = "分享人角色")
    private Integer sharerRole;

    @ApiModelProperty(value = "分享时间")
    private Date shareTime;

    @ApiModelProperty(value = "下载次数")
    private Integer downloadCount;

    @ApiModelProperty(value = "浏览次数")
    private Integer readCount;

    @ApiModelProperty(value = "远程标识 0本地 1远程")
    private Integer remote;

    @ApiModelProperty(value = "用于远程文件的token")
    private String token;


}
