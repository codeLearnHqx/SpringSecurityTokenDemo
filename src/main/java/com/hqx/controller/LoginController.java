package com.hqx.controller;

import com.hqx.domain.LoginRspVO;
import com.hqx.domain.ResponseResult;
import com.hqx.domain.User;
import com.hqx.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 登录接口
 * @Create by hqx
 * @Date 2023/11/2 16:18
 */

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult<LoginRspVO> login(@RequestBody User user) {
        // 登录
        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult<Boolean> logout() {
        return loginService.logout();
    }

}
