package com.yueqian.xk.mapper;

import com.yueqian.xk.beans.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户dao操作
 */
@Mapper
public interface UserMapper {
    /**
     * 用户登录
     * @param user
     * @return
     */
    @Select(value="SELECT * FROM users WHERE username=#{username} AND PASSWORD=#{password}")
    UserInfo login(UserInfo user);
}
