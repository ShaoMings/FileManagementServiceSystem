package com.graduation.model.dto.gitee.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description 码云仓库文件目录树封装类
 *
 * @author shaoming
 * @date 2021/10/22 17:19
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentTreeDto {
    /** 分支名(如master)、Commit或者目录Tree的SHA值 */
    private String sha;
    /** 当前仓库请求api */
    private String url;
    /** 具体目录树内容 */
    private List<TreeDto> tree;
    /** 是否损坏或者有删节 */
    private Boolean truncated;
}
