package com.graduation.model.dto.gitee.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 用来记录仓库名与最近更新时间 (utc)
 *
 * @author shaoming
 * @date 2021/10/22 22:31
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepoInfoDto {
    private String repo;
    private String lastModified;
}
