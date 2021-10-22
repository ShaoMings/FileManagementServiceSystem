package com.graduation.model.dto.gitee.response;

import com.graduation.utils.FileSizeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/10/15 15:23
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TreeDto {
    /** 文件相对路径 */
    private String path;
    /** 文件名 */
    private String name;
    /** 未知属性 */
    private String mode;
    /** 文件类型 blob 表示为文件  tree表示为文件夹*/
    private String type;
    /** 文件大小  文件夹为0*/
    private Integer size;

    /** 单位化后的文件大小*/
    private String file_size;

    /** 分支名(如master)、Commit或者目录Tree的SHA值*/
    private String sha;
    /** 文件或文件夹的访问api 文件返回的content 或为base64加密内容 需手动解密*/
    private String url;
    /** 是否为文件夹*/
    private Boolean is_dir;

    private String owner;


    public void setSize(Integer size) {
        this.size = size;
        this.setFile_size(FileSizeConverter.getLength(this.size.longValue()));
    }

    public String getPath() {
        boolean contains = path.contains("/");
        if (this.getSize()>0){
            if (contains){
                this.setName(path.substring(path.lastIndexOf("/")+1));
            }else {
                this.setName(path);
            }
            this.setIs_dir(false);
        }else {
            this.setIs_dir(true);
            if (contains){
                this.setName(path.substring(path.lastIndexOf("/")+1));
            }else {
                this.setName(path);
            }
        }
        String tmp = url.substring(url.indexOf("/repos/") + 7);
        this.setOwner(tmp.substring(0,tmp.indexOf("/")));
        return contains?path.substring(0,path.lastIndexOf("/")):"";
    }
}
