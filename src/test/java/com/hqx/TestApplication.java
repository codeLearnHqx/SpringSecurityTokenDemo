package com.hqx;

import com.hqx.domain.User;
import com.hqx.mapper.MenuMapper;
import com.hqx.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Create by hqx
 * @Date 2023/11/1 23:30
 */
@SpringBootTest
public class TestApplication {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;


    @Test
    void testBCryptPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String encode1 = passwordEncoder.encode("123456");
        String encode2 = passwordEncoder.encode("123456");
        System.out.println(encode1);
        System.out.println(encode2);

        // 明文与密文进行比对
        boolean result1 = passwordEncoder.matches("123456", encode1);
        boolean result2 = passwordEncoder.matches("123456", encode2);

        System.out.println(result1);
        System.out.println(result2);

    }

    @Test
    void testSelectPermsByUserId() {
        List<String> perms = menuMapper.selectPermsByUserId(2L);
        System.out.println(perms);
    }

    @Test
    void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users.toString());
    }

}
