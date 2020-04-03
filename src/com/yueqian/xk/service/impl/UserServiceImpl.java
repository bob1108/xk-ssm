package com.yueqian.xk.service.impl;

import com.yueqian.xk.beans.UserInfo;
import com.yueqian.xk.mapper.UserMapper;
import com.yueqian.xk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    @Override
    public UserInfo login(UserInfo userInfo) {
        return userMapper.login(userInfo);
    }
}
