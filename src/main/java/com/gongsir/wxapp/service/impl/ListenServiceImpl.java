package com.gongsir.wxapp.service.impl;

import com.gongsir.wxapp.mapper.ListenMapper;
import com.gongsir.wxapp.model.Listen;
import com.gongsir.wxapp.model.ListenExample;
import com.gongsir.wxapp.service.ListenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 龚涛
 * @date 2019/11/1 09:05
 * 编码不要畏惧变化，要拥抱变化
 */
@Service
public class ListenServiceImpl implements ListenService {
    @Resource
    ListenMapper listenMapper;
    /**
     * 添加证件监听信息
     *
     * @param listen 需要监听的证件信息
     * @return 主键id
     */
    @Override
    public int addListen(Listen listen) {
        return listenMapper.insert(listen);
    }

    /**
     * 查询监听列表
     *
     * @param id id
     * @return 监听信息
     */
    @Override
    public Listen selectByPk(Integer id) {
        return listenMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据证件号搜索监听列表
     *
     * @param lisNum 证件号
     * @return 监听信息集合
     */
    @Override
    public List<Listen> selectByLisNum(String lisNum,String type,String status) {
        ListenExample example = new ListenExample();
        ListenExample.Criteria criteria = example.createCriteria();
        criteria.andLisNumEqualTo(lisNum);
        criteria.andLisTypeEqualTo(type);
        if (type!=null){
            criteria.andLisTypeEqualTo(type);
        }
        if (status!=null){
            criteria.andLisStatusEqualTo(status);
        }
        example.setDistinct(true);
        example.setOrderByClause("id desc");
        return listenMapper.selectByExample(example);
    }

    /**
     * 根据用户查找
     *
     * @param openid 用户身份
     * @param type   证件类型,null不区分类型
     * @param status 状态
     * @return 集合
     */
    @Override
    public List<Listen> selectByOpenId(String openid, String num, String status) {
        ListenExample example = new ListenExample();
        ListenExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openid);
        if (num!=null){
            criteria.andLisNumEqualTo(num);
        }
        if (status!=null){
            criteria.andLisStatusEqualTo(status);
        }
        example.setDistinct(true);
        example.setOrderByClause("id desc");
        return listenMapper.selectByExample(example);
    }

    /**
     * 更新formId
     *
     * @param listen 信息
     */
    @Override
    public int updateListenFormIdByPk(Listen listen) {
        return listenMapper.updateByPrimaryKeySelective(listen);
    }

    /**
     * 通过证件号更新listen
     * @param listen 欲更新的字段
     * @return num
     */
    @Override
    public int updateListenByCardNum(Listen listen) {
        ListenExample example = new ListenExample();
        ListenExample.Criteria criteria = example.createCriteria();
        criteria.andLisNumEqualTo(listen.getLisNum());
        return listenMapper.updateByExampleSelective(listen,example);
    }

    /**
     * 删除单个实体
     *
     * @param id id
     * @return rs
     */
    @Override
    public int deleteListenById(int id) {
        return listenMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return rs
     */
    @Override
    public int deleteListensByIds(List<Integer> ids) {
        ListenExample example = new ListenExample();
        ListenExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return listenMapper.deleteByExample(example);
    }

    /**
     * 后台获取所有监听信息
     *
     * @param num    证件号
     * @param status 认领状态
     * @param page   页码
     * @param limit  每页显示数量
     * @return listens
     */
    @Override
    public List<Listen> getAllListens(String num, String status, int page, int limit) {
        page = Math.max(page,1);
        int offset = (page-1)*limit;
        ListenExample example = new ListenExample();
        ListenExample.Criteria criteria = example.createCriteria();
        if (null != num){
            criteria.andLisNumLike("%"+num+"%");
        }
        if (null != status){
            if ("ok".equalsIgnoreCase(status)){
                criteria.andLisStatusNotEqualTo("no");
            }else {
                criteria.andLisStatusEqualTo(status);
            }
        }
        example.setLimit(limit);
        example.setOffset(offset);
        return listenMapper.selectByExample(example);
    }

    @Override
    public Long countAllListens(String num, String status) {
        ListenExample example = new ListenExample();
        ListenExample.Criteria criteria = example.createCriteria();
        if (null != num){
            criteria.andLisNumLike("%"+num+"%");
        }
        if (null != status){
            if ("ok".equalsIgnoreCase(status)){
                criteria.andLisStatusNotEqualTo("no");
            }else {
                criteria.andLisStatusEqualTo(status);
            }
        }
        return listenMapper.countByExample(example);
    }
}
