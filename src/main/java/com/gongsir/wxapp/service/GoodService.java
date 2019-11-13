package com.gongsir.wxapp.service;

import com.gongsir.wxapp.model.Good;

import java.util.List;

/**
 * @author 龚涛
 * @date 2019/10/24 19:33
 * 编码不要畏惧变化，要拥抱变化
 */
public interface GoodService {
    /**
     * 插入新的物品记录
     * @param good 丢失物品信息
     * @return 主键id
     */
    int saveGood(Good good);

    /**
     * 根据主键id删除物品信息
     * @param id 主键pk
     * @return 记录数
     */
    int deleteGoodByPk(Integer id);

    /**
     * 通过id修改某一项信息,不能修改id和openid
     * @param good 需要修改的信息
     * @return 记录数
     */
    int updateGoodByPk(Good good);

    /**
     * 通过主键id查找
     * @param id 主键,唯一
     * @return good
     */
    Good selectBGoodyPk(Integer id);

    /**
     * 通过openID查找用户发布的信息
     * @param openID 用户表示
     * @param page 页码
     * @param limit 每页显示数量
     * @return list集合
     */
    List<Good> selectGoodsByOpenID(String openID,int page,int limit);
    long countByOpenID(String openID);


    /**
     * 查询所有good信息
     * @param page 页码
     * @param limit 每页显示数量
     * @return list集合
     */
    List<Good> selectAllGoods(int page, int limit);
    long getAllCount();

    /**
     * 按物品分类查询
     * @param goodClass 物品分类名称
     * @return list集合
     */
    List<Good> selectGoodsByClass(String goodClass, int page, int limit);
    long countByClass(String goodClass);

    /**
     * 关键字查询
     * @param keyword 关键字
     * @param page 页码
     * @param limit 数量
     * @return list集合
     */
    List<Good> selectByKeyWords(String keyword,int page,int limit);
    long countByKeyWords(String keyword);
}