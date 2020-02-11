package com.gongsir.wxapp.controller.wxapi;

import com.gongsir.wxapp.model.Listen;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class ListenControllerTest {

    @Resource
    ListenController listener;

    @Test
    void listenCard() {
//        RedisCacheUtil.redisTemplate.opsForValue().set("aa","201731061426");
//        System.out.println(RedisCacheUtil.redisTemplate.opsForValue().get("aa"));
        Listen listen = new Listen();
        listen.setLisType("stuCard");
        listen.setLisNum("201731061426");
        listener.listenCard(listen,"RTBCNDE2M0ZEQ0QxOUMzNzkxQjQ5QjY0RURCOUY2ODg=");
    }
}