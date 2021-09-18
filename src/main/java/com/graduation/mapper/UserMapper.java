package com.graduation.mapper;

import com.graduation.model.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户ID获取用户名 包括逻辑删除的用户
     * @param id 用户id
     * @return 用户名
     */
    @Select("select username from user where id = #{id}")
    String getUserName(@Param("id") Integer id);

}
