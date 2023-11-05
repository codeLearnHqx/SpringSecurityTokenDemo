package com.hqx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description
 * @Create by hqx
 * @Date 2023/11/1 23:00
 */
@SpringBootApplication
@MapperScan("com.hqx.mapper") // 扫描mapper
public class TokenApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TokenApplication.class, args);
        System.out.println(111);
    }

}
