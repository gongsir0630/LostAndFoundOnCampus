package com.gongsir.wxapp.controller.wxapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author 龚涛
 * @date 2019/12/4 20:19
 * 编码不要畏惧变化，要拥抱变化
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CardControllerTest {

    @Resource
    CardController cardController;

    @Test
    public void cardHasFound() {
        cardController.cardHasFound("b3ZELVE0cHYxeDQ2Y0hmc2wzYmRwcnl1bXZYUQ==/gongSir/dkgzYTdLTGJ3YU1STWcrR0p0YXJ4QT09");
    }
}