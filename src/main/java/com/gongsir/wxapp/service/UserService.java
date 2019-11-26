package com.gongsir.wxapp.service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 龚涛
 * @date 2019/10/24 10:42
 * 编码不要畏惧变化，要拥抱变化
 */
public interface UserService {
    /**
     * 用户注册,绑定微信openID
     * @param user 用户信息
     * @return 主键id
     */
    int saveUser(User user) throws SQLException;

    /**
     * 通过openID查询用户信息
     * @param openID 唯一身份标识
     * @return 返回用户信息
     */
    User selectUserByOpenID(String openID);

    /**
     * 更新用户不为null的字段
     * @param user 需要更改的信息的封装
     * @param openID 身份标识
     * @return 返回操作成功记录数
     */
    int updateUserByOpenID(User user,String openID);

    /**
     * 通过学号查找用户
     * @param stuNum 学号
     * @return 用户信息
     */
    List<User> selectUsersByStuNum(String stuNum);
}
