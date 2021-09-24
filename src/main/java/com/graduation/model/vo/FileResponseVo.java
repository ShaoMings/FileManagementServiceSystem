package com.graduation.model.vo;

import com.graduation.utils.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 文件信息响应对象
 *
 * @author shaoming
 * @date 2021/8/16 22:11
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponseVo {

    private int code;
    private String msg;
    private Object data;


    /**
     *  成功状态响应  无数据
     * @return 响应对象
     */
    public static FileResponseVo success(){
        return new FileResponseVo(Constant.SUCCESS_STATUS_CODE,"success","");
    }

    /**
     *  成功状态响应  有数据
     * @param data 数据对象
     * @return 响应对象
     */
    public static FileResponseVo success(Object data){
        return new FileResponseVo(Constant.SUCCESS_STATUS_CODE,"success",data);
    }

    /**
     * 成功状态响应  自定义提示信息  有数据
     * @param msg 自定义提示信息
     * @param data 数据
     * @return 响应对象
     */
    public static FileResponseVo success(String msg,Object data){
        return new FileResponseVo(Constant.SUCCESS_STATUS_CODE,msg,data);
    }

    /**
     * 失败状态响应 自定义提示信息 无数据
     * @param msg 自定义提示信息
     * @return 响应对象
     */
    public static FileResponseVo fail(String msg){
      return new FileResponseVo(Constant.INTERNAL_ERROR_CODE,msg,"");
    }


    /**
     *  失败状态响应 自定义提示信息 有数据
     * @param msg 自定义提示信息
     * @param data 数据
     * @return 响应对象
     */
    public static FileResponseVo fail(String msg,Object data){
        return new FileResponseVo(Constant.INTERNAL_ERROR_CODE,msg,data);
    }

}
