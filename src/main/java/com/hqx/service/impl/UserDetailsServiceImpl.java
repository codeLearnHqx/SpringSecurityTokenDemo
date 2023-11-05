package com.hqx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hqx.domain.LoginUser;
import com.hqx.domain.User;
import com.hqx.mapper.MenuMapper;
import com.hqx.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description 提供核心用户信息
 * @Create by hqx
 * @Date 2023/11/2 11:56
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);
        // 如果没有查询到用户
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名错误");
        }
        // 查询用户对应的权限
        List<String> permissions = menuMapper.selectPermsByUserId(user.getId());
        // 将数据封装成UserDetails返回
        return new LoginUser(user, permissions);
    }
}
