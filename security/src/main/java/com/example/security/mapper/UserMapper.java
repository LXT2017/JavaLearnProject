package com.example.security.mapper;

import com.example.security.pojo.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author shawn
 * @version 1.0
 * @ClassName UserMapper
 * Description:
 * @date 2023/2/27 20:19
 */
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);
}
