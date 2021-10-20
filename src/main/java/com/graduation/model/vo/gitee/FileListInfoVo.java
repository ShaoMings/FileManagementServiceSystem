package com.graduation.model.vo.gitee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/10/15 21:29
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileListInfoVo {
    /** 文件相对路径 */
    private String path;
    /** 文件名 */
    private String name;
    /** 文件类型 blob 表示为文件  tree表示为文件夹*/
    private String type;
    /** 文件大小  文件夹为0*/
    private String size;
    /** 分支名(如master)、Commit或者目录Tree的SHA值*/
    private String sha;
    /** 是否为文件夹*/
    private Boolean is_dir;
}
