package com.gongsir.wxapp.service;

import com.gongsir.wxapp.model.Listen;

import java.util.List;

/**
 * @author 龚涛
 * @date 2019/10/30 08:45
 * 编码不要畏惧变化，要拥抱变化
 */
public interface ListenService {
    /**
     * 添加证件监听信息
     * @param listen 需要监听的证件信息
     * @return 主键id
     */
    int addListen(Listen listen);

    /**
     * 查询监听列表
     * @param id id
     * @return 监听信息
     */
    Listen selectByPk(Integer id);

    /**
     * 根据证件号搜索监听列表
     * @param lisNum 证件号
     * @param type 证件类型
     * @return 监听信息集合
     */
    List<Listen> selectByLisNum(String lisNum,String type,String status);

    /**
     * 根据用户查找
     * @param openid 用户身份
     * @param type 证件类型,null不区分类型
     * @param status 状态
     * @return 集合
     */
    List<Listen> selectByOpenId(String openid,String type,String status);

    /**
     * 更新formId
     * @param listen 信息
     */
    int updateListenFormIdByPk(Listen listen);
}
