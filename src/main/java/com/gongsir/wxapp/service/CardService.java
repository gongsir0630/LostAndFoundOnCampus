package com.gongsir.wxapp.service;

import com.gongsir.wxapp.model.Card;

import java.util.List;

/**
 * @author 龚涛
 * @date 2019/10/28 10:47
 * 编码不要畏惧变化，要拥抱变化
 */
public interface CardService {
    /**
     * 添加证件丢失信息
     * @param card 证件信息
     * @return 返回主键id
     */
    int saveCard(Card card);

    /**
     * 根据主键id删除物品信息
     * @param id 主键pk
     * @return 记录数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 通过id查找证件信息
     * @param id 主键id
     * @return 证件信息
     */
    Card selectByPk(Integer id);

    /**
     * 通过证件号和状态查找证件信息
     * @param num 证件号码
     * @param status 证件状态:ok/no
     * @return list集合
     */
    List<Card> selectByNumAndStatus(String num,String status);

    /**
     * 更新非空信息
     * @param card 更新的信息
     * @return 成功记录数
     */
    int updateByPk(Card card);

    /**
     * 通过openID查找用户发布的信息
     * @param openid 用户表示
     * @param page 页码
     * @param limit 每页显示数量
     * @return list集合
     */
    List<Card> selectByOpenId(String openid, int page, int limit);

    /**
     * 分页数量
     * @param openid 用户id
     * @return int
     */
    long countByOpenId(String openid);

    /**
     * 已经认领的证件
     * @param stuNum 学号
     * @return list集合
     */
    List<Card> hasFound(String stuNum);

    /**
     * 批量删除
     * @param ids id集合
     * @return rs
     */
    int deleteCardsByIds(List<Integer> ids);

    /**
     * 管理后台获取所有数据
     * @param num 证件号
     * @param type 证件类型
     * @param page 页码
     * @param limit 每页显示数量
     * @return cards
     */
    List<Card> getAllCards(String num,String type,int page,int limit);
    long countAllCards(String num, String type);
}
