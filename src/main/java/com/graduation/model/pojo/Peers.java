package com.graduation.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Peers对象", description="")
public class Peers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "集群id")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "集群名称")
    private String name;

    @ApiModelProperty(value = "组名")
    private String groupName;

    @ApiModelProperty(value = "集群服务地址")
    private String serverAddress;

    @ApiModelProperty(value = "访问域名")
    private String showAddress;

    @ApiModelProperty(value = "磁盘空间总大小")
    private String diskTotalSize;

    @ApiModelProperty(value = "磁盘剩余可用空间大小")
    private String diskLeftSize;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
