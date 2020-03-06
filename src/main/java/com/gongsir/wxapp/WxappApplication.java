package com.gongsir.wxapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 龚涛
 * @date 2019-10-23 21:23:36
 * app启动类
 */
@SpringBootApplication
//        (exclude = {SecurityAutoConfiguration.class})
/**
 * 扫描mapper接口
 */
@MapperScan("com.gongsir.wxapp.mapper")
public class WxappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxappApplication.class, args);
    }

}
