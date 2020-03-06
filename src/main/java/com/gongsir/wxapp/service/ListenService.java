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
     * @param num 证件号
     * @param status 状态
     * @return 集合
     */
    List<Listen> selectByOpenId(String openid,String num,String status);

    /**
     * 更新formId
     * @param listen 信息
     */
    int updateListenFormIdByPk(Listen listen);

    /**
     * 根据证件号更新listen信息
     * @param listen 欲更新的字段
     * @return 操作成功的行数
     */
    int updateListenByCardNum(Listen listen);

    /**
     * 删除单个实体
     * @param id id
     * @return rs
     */
    int deleteListenById(int id);

    /**
     * 批量删除
     * @param ids id集合
     * @return rs
     */
    int deleteListensByIds(List<Integer> ids);

    /**
     * 后台获取所有监听信息
     * @param num 证件号
     * @param status 认领状态
     * @param page 页码
     * @param limit 每页显示数量
     * @return listens
     */
    List<Listen> getAllListens(String num,String status,int page, int limit);
    Long countAllListens(String num,String status);
}
