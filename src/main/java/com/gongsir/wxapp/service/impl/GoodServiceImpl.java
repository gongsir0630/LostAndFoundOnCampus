package com.gongsir.wxapp.service.impl;

import com.gongsir.wxapp.mapper.GoodMapper;
import com.gongsir.wxapp.mapper.UserMapper;
import com.gongsir.wxapp.model.Good;
import com.gongsir.wxapp.model.GoodExample;
import com.gongsir.wxapp.model.User;
import com.gongsir.wxapp.model.UserExample;
import com.gongsir.wxapp.service.GoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    UserMapper userMapper;

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
        example.setOrderByClause("id desc");
        return goodMapper.selectByExample(example);
    }

    @Override
    public long countByOpenID(String openID) {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openID);
        return goodMapper.countByExample(example);
    }

    @Override
    public List<Good> selectByStuNum(String stuNum, int page, int limit) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andStuNumEqualTo(stuNum);
        List<User> users = userMapper.selectByExample(example);
        List<String> openids = new ArrayList<>();
        for (User user :
                users) {
            openids.add(user.getUserOpenid());
        }
        GoodExample example1 = new GoodExample();
        GoodExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andOpenidIn(openids);
        criteria1.andGoodStatusEqualTo("no");
        page = Math.max(page, 1);
        int offset= (page-1)*limit;
        example1.setOffset(offset);
        example1.setLimit(limit);
        example1.setOrderByClause("id desc");
        return goodMapper.selectByExample(example1);
    }

    @Override
    public long countByStuNum(String stuNum) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andStuNumEqualTo(stuNum);
        List<User> users = userMapper.selectByExample(example);
        List<String> openids = new ArrayList<>();
        for (User user :
                users) {
            openids.add(user.getUserOpenid());
        }
        GoodExample example1 = new GoodExample();
        GoodExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andOpenidIn(openids);
        criteria1.andGoodStatusEqualTo("no");
        return goodMapper.countByExample(example1);
    }

    /**
     * 查询所有good信息
     *
     * @return list集合
     */
    @Override
    public List<Good> selectAllGoods(int page, int limit, int sort) {
        page = Math.max(page, 1);
        int offset= (page-1)*limit;
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        //只显示没有找到的物品信息
        criteria.andGoodStatusEqualTo("no");
        example.setOffset(offset);
        example.setLimit(limit);
        if (sort==1){
            example.setOrderByClause("id asc");
        }else {
            example.setOrderByClause("id desc");
        }
        return goodMapper.selectByExample(example);
    }

    @Override
    public long getAllCount() {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        //检索未找到的物品信息
        criteria.andGoodStatusEqualTo("no");
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
        criteria.andGoodStatusEqualTo("no");
        example.setOffset(offset);
        example.setLimit(limit);
        example.setOrderByClause("id desc");
        return goodMapper.selectByExample(example);
    }

    @Override
    public long countByClass(String goodClass) {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andGoodClassEqualTo(goodClass);
        criteria.andGoodStatusEqualTo("no");
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
        criteria.andGoodStatusEqualTo("no");
        example.or(example.createCriteria().andGoodTextsLike('%'+keyword+'%').andGoodStatusEqualTo("no"));
        example.setOffset(offset);
        example.setLimit(limit);
        example.setOrderByClause("id desc");
        return goodMapper.selectByExample(example);
    }

    @Override
    public long countByKeyWords(String keyword) {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andGoodTitleLike('%'+keyword+'%');
        criteria.andGoodStatusEqualTo("no");
        example.or(example.createCriteria().andGoodTextsLike('%'+keyword+'%').andGoodStatusEqualTo("no"));
        return goodMapper.countByExample(example);
    }

    /**
     * 根据状态查找,可用于认领
     * @param status 状态
     * @param page 页数
     * @param limit 数量
     * @return good集合
     */
    @Override
    public List<Good> selectByGoodStatus(String status, int page, int limit) {
        GoodExample example = new GoodExample();
        page = Math.max(page,1);
        int offset = (page-1)*limit;
        GoodExample.Criteria criteria = example.createCriteria();
        if (null != status){
            if ("ok".equalsIgnoreCase(status)){
                criteria.andGoodStatusNotEqualTo("no");
            }else {
                criteria.andGoodStatusEqualTo(status);
            }
        }
        example.setLimit(limit);
        example.setOffset(offset);
        example.setOrderByClause("id desc");
        return goodMapper.selectByExample(example);
    }

    @Override
    public long countByGoodStatus(String status) {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        if (null != status){
            if ("ok".equalsIgnoreCase(status)){
                criteria.andGoodStatusNotEqualTo("no");
            }else {
                criteria.andGoodStatusEqualTo(status);
            }
        }
        return goodMapper.countByExample(example);
    }

    /**
     * 批量删除good
     *
     * @param ids id集合
     * @return rs
     */
    @Override
    public int deleteGoodByIds(List<Integer> ids) {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return goodMapper.deleteByExample(example);
    }
}
