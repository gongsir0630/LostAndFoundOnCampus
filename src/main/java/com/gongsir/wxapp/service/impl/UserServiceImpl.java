package com.gongsir.wxapp.service.impl;

import com.gongsir.wxapp.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 龚涛
 * @date 2019/10/24 11:07
 * 编码不要畏惧变化，要拥抱变化
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 注入mapper接口
     */
    @Resource
    UserMapper userMapper;

    /**
     * 用户注册,绑定微信openID
     *
     * @param user 用户信息
     * @return 主键id
     */
    @Override
    public int saveUser(User user) {
        return userMapper.insert(user);
    }

    /**
     * 通过openID查询用户信息
     *
     * @param openID 唯一身份标识
     * @return 返回用户信息
     */
    @Override
    public User selectUserByOpenID(String openID) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserOpenidEqualTo(openID);
        List<User> users = userMapper.selectByExample(example);
        if (users.size()!=0){
            return users.get(0);
        }
        return null;
    }

    /**
     * 更新用户不为null的字段
     *
     * @param user   需要更改的信息的封装
     * @param openID 身份标识
     * @return 返回操作成功记录数
     */
    @Override
    public int updateUserByOpenID(User user, String openID) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserOpenidEqualTo(openID);
        return userMapper.updateByExampleSelective(user, example);
    }

    /**
     * 通过学号查找用户
     *
     * @param stuNum 学号
     * @return 用户信息
     */
    @Override
    public List<User> selectUsersByStuNum(String stuNum) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andStuNumEqualTo(stuNum);
        return userMapper.selectByExample(example);
    }
}
