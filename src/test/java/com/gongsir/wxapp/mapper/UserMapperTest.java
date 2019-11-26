package com.gongsir.wxapp.mapper;

import com.gongsir.wxapp.WxappApplication;
import com.gongsir.wxapp.model.User;
import com.gongsir.wxapp.model.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 龚涛
 * @date 2019/10/23 19:43
 * 编码不要畏惧变化，要拥抱变化
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WxappApplication.class})
public class UserMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);

    @Resource
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUserOpenid("sasdfgthjkyumynrtbrvfdfegrtnmynrtbr");
        user.setUserName("gongSir");
        user.setStuNum("201731061426");
        userMapper.insert(user);
    }

    @Test
    public void selectByExample() {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andStuNumEqualTo("201731061426");
        List<User> users = userMapper.selectByExample(example);
        logger.info("user:{}",users);
    }

    @Test
    public void updateByExampleSelective() {
        User user = new User();
        user.setStuNum("201731061426");
        user.setUserName("龚涛");
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andStuNumEqualTo(user.getStuNum());
        userMapper.updateByExampleSelective(user,example);
    }
}