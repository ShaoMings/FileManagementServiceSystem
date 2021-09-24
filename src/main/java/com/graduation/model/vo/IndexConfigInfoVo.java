package com.graduation.model.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description 首页配置信息响应对象
 *
 * @author shaoming
 * @date 2021/8/17 14:57
 * @since 1.0
 */
@Data
public class IndexConfigInfoVo implements Serializable {
    private static final long serialVersionUID = 637655885822474661L;
    private String addr;
    private List<String> peers;
    @JSONField(name = "enable_https")
    private boolean enableHttps;
    private String group;
    @JSONField(name = "rename_file")
    private boolean renameFile;
    @JSONField(name = "show_dir")
    private boolean showDir;
    private List<String> extensions;
    @JSONField(name = "refresh_interval")
    private String refreshInterval;
    @JSONField(name = "enable_web_upload")
    private boolean enableWebUpload;
    @JSONField(name = "download_domain")
    private String downloadDomain;
    @JSONField(name = "enable_custom_path")
    private boolean enableCustomPath;
    private List<String> scenes;
    @JSONField(name = "alarm_receivers")
    private List<String> alarmReceivers;
    @JSONField(name = "default_scene")
    private String defaultScene;
    private JSONObject mail;
    @JSONField(name = "alarm_url")
    private String alarmUrl;
    @JSONField(name = "download_use_token")
    private boolean downloadUseToken;
    @JSONField(name = "download_token_expire")
    private Long downloadTokenExpire;
    @JSONField(name = "queue_size")
    private Long queueSize;
    @JSONField(name = "auto_repair")
    private boolean autoRepair;
    private String host;
    @JSONField(name = "file_sum_arithmetic")
    private String fileSumArithmetic;
    @JSONField(name = "peer_id")
    private String peerId;
    @JSONField(name = "support_group_manage")
    private boolean supportGroupManage;
    @JSONField(name = "admin_ips")
    private List<String> adminIps;
    @JSONField(name = "enable_merge_small_file")
    private boolean enableMergeSmallFile;
    @JSONField(name = "enable_migrate")
    private boolean enableMigrate;
    @JSONField(name = "enable_distinct_file")
    private boolean enableDistinctFile;
    @JSONField(name = "read_only")
    private boolean readOnly;
    @JSONField(name = "enable_cross_origin")
    private boolean enableCrossOrigin;
    @JSONField(name = "enable_google_auth")
    private boolean enableGoogleAuth;
    @JSONField(name = "auth_url")
    private String authUrl;
    @JSONField(name = "enable_download_auth")
    private boolean enableDownloadAuth;
    @JSONField(name = "default_download")
    private boolean defaultDownload;
    @JSONField(name = "enable_tus")
    private boolean enableTus;
    @JSONField(name = "sync_timeout")
    private Long syncTimeout;
    @JSONField(name = "enable_fsnotify")
    private boolean enableFsnotify;
    @JSONField(name = "enable_disk_cache")
    private boolean enableDiskCache;
    @JSONField(name = "connect_timeout")
    private boolean connectTimeout;
    @JSONField(name = "read_timeout")
    private Long readTimeout;
    @JSONField(name = "write_timeout")
    private Long writeTimeout;
    @JSONField(name = "idle_timeout")
    private Long idleTimeout;
    @JSONField(name = "read_header_timeout")
    private Long readHeaderTimeout;
    @JSONField(name = "sync_worker")
    private Long syncWorker;
    @JSONField(name = "upload_worker")
    private Long uploadWorker;
    @JSONField(name = "upload_queue_size")
    private Long uploadQueueSize;
    @JSONField(name = "retry_count")
    private Long retryCount;
    @JSONField(name = "sync_delay")
    private Long syncDelay;
    @JSONField(name = "watch_chan_size")
    private Long watchChanSize;
}
