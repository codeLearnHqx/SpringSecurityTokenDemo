package com.hqx.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 继承于security的UserDetails接口
 * @Create by hqx
 * @Date 2023/11/2 12:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    // 自定义得User类
    private User user;

    // 当前用户的权限信息
    private List<String> permissions;

    // 转换后的用户权限
    @JSONField(serialize = false) // FastJson的注解，在使用FastJson进行序列化时该属性会被忽略
    private List<GrantedAuthority> authorities;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    /**
     * security会调用该方法来校验用户的权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 为了避免鉴权时多次调用该方法导致authorities对象多次创建
        if (authorities != null) {
            return authorities;
        }
        // 将用户权限封装到GrantedAuthority类型的集合中返回给security调用
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否已启用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
