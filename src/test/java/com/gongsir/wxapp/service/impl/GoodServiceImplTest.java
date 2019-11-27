package com.gongsir.wxapp.service.impl;

import com.gongsir.wxapp.model.Good;
import com.gongsir.wxapp.service.GoodService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GoodServiceImplTest {

    @Resource
    GoodService goodService;

    @Test
    void selectByStuNum() {
        List<Good> goods = goodService.selectByStuNum("201731103115", 1, 5);
        goods.forEach(System.out::println);
    }

    @Test
    void countByStuNum() {
    }
}