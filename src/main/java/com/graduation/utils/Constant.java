package com.graduation.utils;

/**
 * Description 常量工具类
 *
 * @author shaoming
 * @date 2021/8/16 16:42
 * @since 1.0
 */
public class Constant {

    /** 空值常量 */
    public static final String NULL_VALUE = "null";

    /** API 响应成功标识 */
    public static final String API_STATUS_SUCCESS = "ok";

    /** 配置管理重载API */
    public static final String API_RELOAD = "/reload";

    /** 文件统计信息API */
    public static final String API_STAT = "/stat";

    /** 文件上传API */
    public static final String API_UPLOAD = "/upload";

    /** 文件删除API */
    public static final String API_DELETE = "/delete";

    /** 获取文件信息 API */
    public static final String API_GET_FILE_INFO = "/get_file_info";

    /** 获取文件列表API */
    public static final String API_LIST_DIR = "/list_dir";

    /** 修复统计信息API */
    public static final String API_REPAIR_STAT = "/repair_stat";

    /** 移除空目录API */
    public static final String API_REMOVE_EMPTY_DIR = "/remove_empty_dir";

    /** 备份操作API */
    public static final String API_BACKUP = "/backup";

    /** 同步失败修复API */
    public static final String API_REPAIR = "/repair";

    /** 从文件目录中修复元数据API */
    public static final String API_REPAIR_FILE_INFO = "/repair_fileinfo";

    /**  状态API */
    public static final String API_STATUS = "/status";

    /**  转换单位大小 */
    public static final Long SIZE_K = 1024L;

}
