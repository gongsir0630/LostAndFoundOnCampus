package com.gongsir.wxapp.service.impl;

import com.gongsir.wxapp.mapper.GoodMapper;
import com.gongsir.wxapp.model.Good;
import com.gongsir.wxapp.model.GoodExample;
import com.gongsir.wxapp.service.GoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 龚涛
 * @date 2019/10/25 13:57
 * 编码不要畏惧变化，要拥抱变化
 */
@Service
public class GoodServiceImpl implements GoodService {
    @Resource
    GoodMapper goodMapper;

    /**
     * 插入新的物品记录
     *
     * @param good 丢失物品信息
     * @return 主键id
     */
    @Override
    public int saveGood(Good good) {
        return goodMapper.insert(good);
    }

    /**
     * 根据主键id删除物品信息
     *
     * @param id 主键pk
     * @return 记录数
     */
    @Override
    public int deleteGoodByPk(Integer id) {
        return goodMapper.deleteByPrimaryKey(id);
    }

    /**
     * 通过id修改某一项信息,不能修改id和openid
     *
     * @param good 需要修改的信息
     * @return 记录数
     */
    @Override
    public int updateGoodByPk(Good good) {
        return goodMapper.updateByPrimaryKeySelective(good);
    }

    /**
     * 通过主键id查找
     *
     * @param id 主键,唯一
     * @return good
     */
    @Override
    public Good selectBGoodyPk(Integer id) {
        return goodMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过openid查找,查找该用户发布的所有信息
     *
     * @param openID 用户id
     * @return list集合
     */
    @Override
    public List<Good> selectGoodsByOpenID(String openID, int page, int limit) {
        page = Math.max(page,1);
        int offset = (page-1)*limit;
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openID);
        example.setOffset(offset);
        example.setLimit(limit);
        return goodMapper.selectByExample(example);
    }

    @Override
    public long countByOpenID(String openID) {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openID);
        return goodMapper.countByExample(example);
    }

    /**
     * 查询所有good信息
     *
     * @return list集合
     */
    @Override
    public List<Good> selectAllGoods(int page, int limit) {
        page = Math.max(page, 1);
        int offset= (page-1)*limit;
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        example.setOffset(offset);
        example.setLimit(limit);
        return goodMapper.selectByExample(example);
    }

    @Override
    public long getAllCount() {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return goodMapper.countByExample(example);
    }

    /**
     * 按物品分类查询
     *
     * @param goodClass 物品分类名称
     * @return list集合
     */
    @Override
    public List<Good> selectGoodsByClass(String goodClass, int page, int limit) {
        page = Math.max(page, 1);
        int offset = (page-1)*limit;
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andGoodClassEqualTo(goodClass);
        example.setOffset(offset);
        example.setLimit(limit);
        return goodMapper.selectByExample(example);
    }

    @Override
    public long countByClass(String goodClass) {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andGoodClassEqualTo(goodClass);
        return goodMapper.countByExample(example);
    }

    /**
     * 关键字查询
     *
     * @param keyword 关键字
     * @param page    页码
     * @param limit   数量
     * @return list集合
     */
    @Override
    public List<Good> selectByKeyWords(String keyword, int page, int limit) {
        page = Math.max(page, 1);
        int offset = (page-1)*limit;
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andGoodTitleLike('%'+keyword+'%');
        example.or(example.createCriteria().andGoodTextsLike('%'+keyword+'%'));
        example.setOffset(offset);
        example.setLimit(limit);
        return goodMapper.selectByExample(example);
    }

    @Override
    public long countByKeyWords(String keyword) {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andGoodTitleLike('%'+keyword+'%');
        example.or(example.createCriteria().andGoodTextsLike('%'+keyword+'%'));
        return goodMapper.countByExample(example);
    }
}
