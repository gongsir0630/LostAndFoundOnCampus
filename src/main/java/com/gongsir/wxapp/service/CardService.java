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
}
