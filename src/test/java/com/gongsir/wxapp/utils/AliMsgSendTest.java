package com.gongsir.wxapp.utils;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author gongsir
 * @date 2020/2/13 17:52
 * 编码不要畏惧变化，要拥抱变化
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class AliMsgSendTest {

    @Test
    void sendVerification() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","123456");

        AliMsgSend.sendVerification("西柚失寻","SMS_183267945","17361040630",AliMsgSend.getCode());
    }
}