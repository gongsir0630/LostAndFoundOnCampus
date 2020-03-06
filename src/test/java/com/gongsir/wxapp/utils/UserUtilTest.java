package com.gongsir.wxapp.utils;

import com.gongsir.wxapp.model.Card;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 龚涛
 * @date 2019/11/13 08:07
 * 编码不要畏惧变化，要拥抱变化
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserUtilTest {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void messagePush() {
//        String openid = Base64Util.encodeData("olAW-4vIdX8DTkzftHveDWIlR4zU");
        String openid = "b3ZELVE0cHYxeDQ2Y0hmc2wzYmRwcnl1bXZYUQ==";
        Card card = new Card();
        card.setCardStatus("no");
        card.setCardTime(new Date());
        card.setCardName("龚涛");
        card.setCardNum("201731061426");
        card.setCardType("stuCard");
        card.setId(1);
        card.setRelation("电话:83035678");
        System.out.println(UserUtil.wxMessagePush(openid,card));
    }
}