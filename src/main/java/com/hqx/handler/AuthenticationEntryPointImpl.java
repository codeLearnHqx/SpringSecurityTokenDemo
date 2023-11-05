package com.hqx.handler;

import com.alibaba.fastjson.JSON;
import com.hqx.domain.ResponseResult;
import com.hqx.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 认证(登录失败，或未登录访问受保护的资源)失败异常处理器
 * @Create by hqx
 * @Date 2023/11/3 16:25
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String message = authException.getMessage();
        log.error("自定义的异常信息： " + message);
        ResponseResult<Object> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "用户认证失败，" + message);
        // 处理异常
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
