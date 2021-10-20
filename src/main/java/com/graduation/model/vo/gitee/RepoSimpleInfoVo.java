package com.graduation.model.vo.gitee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/10/19 11:11
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepoSimpleInfoVo {
    private String owner;
    private String name;
    private String path;
}
