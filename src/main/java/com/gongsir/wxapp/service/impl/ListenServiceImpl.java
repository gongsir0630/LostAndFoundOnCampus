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
    public List<Listen> selectByOpenId(String openid, String type, String status) {
        ListenExample example = new ListenExample();
        ListenExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openid);
        criteria.andLisTypeEqualTo(type);
        if (type!=null){
            criteria.andLisTypeEqualTo(type);
        }
        if (status!=null){
            criteria.andLisStatusEqualTo(status);
        }
        example.setDistinct(true);
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
}
