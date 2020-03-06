package com.gongsir.wxapp.service.impl;

import com.gongsir.wxapp.mapper.CardMapper;
import com.gongsir.wxapp.model.Card;
import com.gongsir.wxapp.model.CardExample;
import com.gongsir.wxapp.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 龚涛
 * @date 2019/10/28 10:56
 * 编码不要畏惧变化，要拥抱变化
 */
@Service
public class CardServiceImpl implements CardService {
    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    @Resource
    CardMapper cardMapper;
    /**
     * 添加证件丢失信息
     *
     * @param card 证件信息
     * @return 返回主键id
     */
    @Override
    public int saveCard(Card card) {
        logger.info("CardServiceImpl插入数据:{}",card);
        return cardMapper.insert(card);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cardMapper.deleteByPrimaryKey(id);
    }

    /**
     * 通过id查找证件信息
     *
     * @param id 主键id
     * @return 证件信息
     */
    @Override
    public Card selectByPk(Integer id) {
        return cardMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过证件号和状态查找证件信息
     *
     * @param num    证件号码
     * @param status 证件状态:ok/no
     * @return list集合
     */
    @Override
    public List<Card> selectByNumAndStatus(String num, String status) {
        CardExample example = new CardExample();
        CardExample.Criteria criteria = example.createCriteria();
        criteria.andCardNumEqualTo(num);
        if (status!=null){
            criteria.andCardStatusEqualTo(status);
        }
        example.setOrderByClause("id desc");
        return cardMapper.selectByExample(example);
    }

    @Override
    public int updateByPk(Card card) {
        return cardMapper.updateByPrimaryKeySelective(card);
    }

    @Override
    public List<Card> selectByOpenId(String openid, int page, int limit) {
        page = Math.max(page,1);
        int offset = (page-1)*limit;
        CardExample example = new CardExample();
        CardExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openid);
        example.setOffset(offset);
        example.setLimit(limit);
        example.setOrderByClause("id desc");
        return cardMapper.selectByExample(example);
    }

    @Override
    public long countByOpenId(String openid) {
        CardExample example = new CardExample();
        CardExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openid);
        return cardMapper.countByExample(example);
    }

    /**
     * 已经认领的证件
     *
     * @param stuNum 学号
     * @return list集合
     */
    @Override
    public List<Card> hasFound(String stuNum) {
        CardExample example = new CardExample();
        CardExample.Criteria criteria = example.createCriteria();
        criteria.andCardStatusEqualTo(stuNum);
        example.setOrderByClause("id desc");
        example.setLimit(10);
        example.setOffset(0);
        return cardMapper.selectByExample(example);
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return rs
     */
    @Override
    public int deleteCardsByIds(List<Integer> ids) {
        CardExample example = new CardExample();
        CardExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return cardMapper.deleteByExample(example);
    }

    /**
     * 管理后台获取所有数据
     *
     * @param num   证件号
     * @param type  证件类型
     * @param page  页码
     * @param limit 每页显示数量
     * @return cards
     */
    @Override
    public List<Card> getAllCards(String num, String type, int page, int limit) {
        page = Math.max(page,1);
        int offset = (page-1)*limit;
        CardExample example = new CardExample();
        CardExample.Criteria criteria = example.createCriteria();
        if (num != null && !"".equalsIgnoreCase(num.trim())){
            criteria.andCardNumLike("%"+num+"%");
        }
        if (type != null && !"".equalsIgnoreCase(type.trim())){
            criteria.andCardTypeEqualTo(type);
        }
        example.setLimit(limit);
        example.setOffset(offset);
        return cardMapper.selectByExample(example);
    }

    @Override
    public long countAllCards(String num, String type) {
        CardExample example = new CardExample();
        CardExample.Criteria criteria = example.createCriteria();
        if (num != null && !"".equalsIgnoreCase(num.trim())){
            criteria.andCardNumLike("%"+num+"%");
        }
        if (type != null && !"".equalsIgnoreCase(type.trim())){
            criteria.andCardTypeEqualTo(type);
        }
        return cardMapper.countByExample(example);
    }
}
