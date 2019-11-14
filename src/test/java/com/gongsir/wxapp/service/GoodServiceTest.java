package com.gongsir.wxapp.service;

import com.gongsir.wxapp.model.Good;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 龚涛
 * @date 2019/10/25 14:25
 * 编码不要畏惧变化，要拥抱变化
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodServiceTest {

    @Resource
    GoodService goodService;

    @Test
    public void saveGood() {
        Good good = new Good();
        good.setGoodTitle("龚涛的手机");
        good.setGoodClass("手机");
        good.setGoodTexts("黑色苹果");
        good.setGoodType("lost");
        good.setOpenid("1234567890");
        good.setRelation("qq:3198307775");
        good.setTime(new Date());
        goodService.saveGood(good);
    }

    @Test
    public void deleteGoodByPk() {
    }

    @Test
    public void updateGoodByPk() {
        Good good = new Good();
        good.setId(1);
        good.setGoodTexts("金色苹果");
        goodService.updateGoodByPk(good);
    }

    @Test
    public void selectBGoodyPk() {
        System.out.println(goodService.selectBGoodyPk(1));
    }

    @Test
    public void selectGoodsByOpenID() {
    }

    @Test
    public void selectAllGoods() {
        List<Good> list = goodService.selectAllGoods(3,3,2);
        list.forEach(System.out::println);
    }

    @Test
    public void selectGoodsByKeywords() {
    }

    @Test
    public void selectGoodsByClass() {
    }

    @Test
    public void selectByKeyWords() {
        List<Good> goods = goodService.selectByKeyWords("2", 1, 10);
        for (Good g :
                goods) {
            System.out.println(g);
        }
    }

    @Test
    public void countByKeyWords() {
    }
}