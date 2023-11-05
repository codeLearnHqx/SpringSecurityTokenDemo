package com.hqx.controller;

import com.hqx.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Create by hqx
 * @Date 2023/11/1 23:02
 */

@RestController
public class HelloController {


    @GetMapping("/hello")
    //@PreAuthorize("hasAuthority('system:dept:list')") // 框架自带的校验方法
    @PreAuthorize("@ex.hasAuthority('system:dept:list')") // 自定义的校验方法
    public String hello() {
        return "hello";
    }

    @PostMapping("/testCors")
    public ResponseResult<String> testCors() {
        return new ResponseResult<>(200, "testCors");
    }

}
