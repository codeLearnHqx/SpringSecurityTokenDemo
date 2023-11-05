package com.hqx.service;

import com.hqx.domain.LoginRspVO;
import com.hqx.domain.ResponseResult;
import com.hqx.domain.User;

/**
 * 登录接口
 */
public interface LoginService {

    /**
     * 登录
     * @param user
     * @return
     */
    ResponseResult<LoginRspVO> login(User user);

    /**
     * 退出登录
     */
    ResponseResult<Boolean> logout();
}
