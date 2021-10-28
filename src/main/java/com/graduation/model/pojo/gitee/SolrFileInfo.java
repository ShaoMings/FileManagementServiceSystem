package com.graduation.model.pojo.gitee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;

/**
 * @author shaoming
 * @Description TODO
 * @date 2021/10/28 13:31
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SolrFileInfo {

    @Field("fileName")
    private String fileName;
    @Field("filePath")
    private String filePath;
    @Field("fileRepo")
    private String fileRepo;
}
