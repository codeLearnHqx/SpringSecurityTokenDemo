package com.hqx.config;

import com.hqx.filter.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Description
 * @Create by hqx
 * @Date 2023/11/2 12:24
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    /**
     *  使用security提供的另外一种密码加密器BCryptPasswordEncoder（不使用默认的）
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 暴露AuthenticationManager：定义了认证Authentication的方法
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                // 允许跨域
                .cors()
                .and()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问（anonymous：认证后的用户将不能访问该路径，permitAll：认证和未认证的用户都能访问该路径）
                .antMatchers("/user/login").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
                // 配置认证失败处理器
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                // 配置权限校验失败处理器
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                // 将JwtAuthenticationTokenFilter过滤器添加到UsernamePasswordAuthenticationFilter过滤器之前
                .addFilterAfter(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
