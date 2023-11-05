package com.hqx.handler;

import com.alibaba.fastjson.JSON;
import com.hqx.domain.ResponseResult;
import com.hqx.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 用户权限校验异常处理器
 * @Create by hqx
 * @Date 2023/11/3 16:32
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult<Object> result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "用户权限不足");
        // 处理异常
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
