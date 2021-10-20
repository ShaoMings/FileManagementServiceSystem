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

    /**  响应成功状态码 */
    public static final int SUCCESS_STATUS_CODE = 200;

    /** 服务器内部错误状态码 */
    public static final int INTERNAL_ERROR_CODE = 500;

    /** 配置管理重载API */
    public static final String API_RELOAD = "/reload";

    /** 文件统计信息API */
    public static final String API_STAT = "/stat";

    /** 文件上传API */
    public static final String API_UPLOAD = "/upload";

    /** 文件删除API */
    public static final String API_DELETE = "/delete";

    public static final String API_RENAME = "/rename";

    /** 文件夹创建API */
    public static final String API_MKDIR = "/mkdir";

    /** 获取文件信息 API */
    public static final String API_GET_FILE_INFO = "/get_file_info";

    /** 获取文件列表API */
    public static final String API_LIST_DIR = "/list_dir";

    /** 修复统计信息API */
    public static final String API_REPAIR_STAT = "/repair_stat";

    /** 移除空目录API */
    public static final String API_REMOVE_EMPTY_DIR = "/remove_empty_dir";

    /** 移除目录API */
    public static final String API_REMOVE_DIR = "/remove_dir";

    /** 备份操作API */
    public static final String API_BACKUP = "/backup";

    /** 同步失败修复API */
    public static final String API_REPAIR = "/repair";

    /** 从文件目录中修复元数据API */
    public static final String API_REPAIR_FILE_INFO = "/repair_fileinfo";

    /**  状态API */
    public static final String API_STATUS = "/status";

    /**  用户状态API */
    public static final String API_USER_STATUS = "/userStatus";

    /**  转换单位大小 */
    public static final Long SIZE_K = 1024L;

    /** 30天 表示一个月 */
    public static final Integer MONTHS_DAY = 30;

    public static final String AES_KEY = "jcjssljyalg01234";

    /** 状态常量词 */
    public static final String STATUS_CONSTANT = "status";

    /** 文件暂存路径 */
    public static final String OUTPUT_TMP_FILE_PATH = "src/main/java/com/graduation/utils/tmp/";

    /**  文件名结尾标识文件夹常量 */
    public static final String DIR_FLAG_CONSTANT = "@dir.zip";

    /** 图片jpg格式 */
    public static final String PICTURE_TYPE_JPG = "jpg";

    /** 图片png格式 */
    public static final String PICTURE_TYPE_PNG = "png";

    /** 音频mp3格式 */
    public static final String AUDIO_TYPE_MP3 = "mp3";

    /** 音频m4a格式 */
    public static final String AUDIO_TYPE_M4A = "m4a";

    /** 音频wav格式 */
    public static final String AUDIO_TYPE_WAV = "wav";

    /** 文档pdf格式 */
    public static final String DOCUMENT_TYPE_PDF = "pdf";

    /** 用于生成token的salt */
    public static final String MAKE_AUTH_TOKEN_SALT = "localhost@shaomingauth_token";

    /** 消息标识常量 */
    public static final String PARAM_KEY_MSG = "message";

    /** data 标识常量 */
    public static final String PARAM_KEY_DATA = "data";



}
