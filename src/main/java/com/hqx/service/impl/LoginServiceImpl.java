package com.hqx.service.impl;

import com.hqx.domain.LoginRspVO;
import com.hqx.domain.LoginUser;
import com.hqx.domain.ResponseResult;
import com.hqx.domain.User;
import com.hqx.service.LoginService;
import com.hqx.utils.JwtUtil;
import com.hqx.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Create by hqx
 * @Date 2023/11/2 16:22
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RedisCache redisCache;
    @Resource
    private AuthenticationManager authenticationManager;
    @Override
    public ResponseResult<LoginRspVO> login(User user) {
        // 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证未通过，将不会往下执行，因为认证失败后会抛出AuthenticationException异常，由认证失败处理器所处理
        //if (Objects.isNull(authenticate)) {
        //    throw new RuntimeException("登录失败");
        //}
        // 如果认证通过，使用userId生成一个jwt，jwt存入ResponseResult中返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 把完整的用户信息存入redis，userId作为key
        redisCache.setCacheObject("login:" + userId, loginUser, 180, TimeUnit.MINUTES);
        // 返回token
        return new ResponseResult<>(200, "登录成功", new LoginRspVO(jwt));
    }

    @Override
    public ResponseResult<Boolean> logout() {
        // 获取securityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Long userId = user.getId();
        // 删除redis中的值
        boolean result = redisCache.deleteObject("login:" + userId.toString());
        return result? new ResponseResult<>(200, "注销成功") : new ResponseResult<>(500, "注销失败");
    }
}
