package com.gongsir.wxapp.service;

import com.gongsir.wxapp.model.Notice;

import java.util.List;

/**
 * @author gongsir
 * @date 2020/2/22 14:40
 * 编码不要畏惧变化，要拥抱变化
 */
public interface NoticeService {
    /**
     * 后台获取所有公告信息
     * @param status 公告状态
     * @param page 页码
     * @param limit 每页显示数量
     * @return list
     */
    List<Notice> getAllNotices(String status, int page, int limit);
    long countAllNotices(String status);

    /**
     * 删除单条公告
     * @param id id
     * @return rs
     */
    int deleteNoticeById(int id);

    /**
     * 批量删除公告
     * @param ids id集合
     * @return rs
     */
    int deleteNoticesByIds(List<Integer> ids);

    /**
     * 根据id更新公告信息
     * @param notice 公告信息
     * @return rs
     */
    int updateNoticeById(Notice notice);

    /**
     * 新增公告
     * @param notice 公告
     * @return rs
     */
    int saveNotice(Notice notice);
}
