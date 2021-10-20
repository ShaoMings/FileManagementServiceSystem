package com.graduation.model.vo.gitee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 获取授权用户的仓库信息封装类
 *
 * @author shaoming
 * @date 2021/10/18 14:20
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepoInfoVo {
    private String name;
    private String path;
    private String fullName;
    private String projectCreator;
    private String url;
    private String description;
    private Boolean isPublic;
    private String defaultBranch;
    private String createTime;
    private String lastPushed;
    private String lastUpdated;
}
