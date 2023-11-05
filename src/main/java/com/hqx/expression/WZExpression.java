package com.hqx.expression;

import com.hqx.domain.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description  用于SPEL表达式的自定义security框架的权限校验方法
 * @Create by hqx
 * @Date 2023/11/3 20:20
 */
@Slf4j
@Component("ex")
public class WZExpression {

    public boolean hasAuthority(String authority) {
        log.warn("调用了自定义的权限校验方法");
        // 获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser curLoginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = curLoginUser.getPermissions();
        // 判断用户权限集合中是否存在authority
        return permissions.contains(authority);
    }


}
