package com.graduation.mapper;

import com.graduation.model.pojo.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

    /**
     * 更新文件路径
     * @param prefix 改变部分之前的字符
     * @param oldString 要改变的部分
     * @param newString 要改为的字符串
     * @return 是否更改成功
     */
    @Update("update file set file_path = replace(file_path,#{oldString},#{newString}) where file_path like #{prefix}\"%\"")
     int updatePathString(@Param("prefix") String prefix,@Param("oldString") String oldString, @Param("newString") String newString);
}
