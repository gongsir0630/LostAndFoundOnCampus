package com.gongsir.wxapp.service.impl;

import com.gongsir.wxapp.mapper.NoticeMapper;
import com.gongsir.wxapp.model.Notice;
import com.gongsir.wxapp.model.NoticeExample;
import com.gongsir.wxapp.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gongsir
 * @date 2020/2/22 14:57
 * 编码不要畏惧变化，要拥抱变化
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;
    /**
     * 后台获取所有公告信息
     *
     * @param status 公告状态
     * @param page   页码
     * @param limit  每页显示数量
     * @return list
     */
    @Override
    public List<Notice> getAllNotices(String status, int page, int limit) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        if (null != status && !"".equalsIgnoreCase(status)){
            criteria.andNoticeStatusEqualTo(status);
        }
        page = Math.max(page,1);
        example.setLimit(limit);
        example.setOffset((page-1)*limit);
        return noticeMapper.selectByExample(example);
    }

    @Override
    public long countAllNotices(String status) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        if (null != status && !"".equalsIgnoreCase(status)){
            criteria.andNoticeStatusEqualTo(status);
        }
        return noticeMapper.countByExample(example);
    }

    /**
     * 删除单条公告
     *
     * @param id id
     * @return rs
     */
    @Override
    public int deleteNoticeById(int id) {
        return noticeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除公告
     *
     * @param ids id集合
     * @return rs
     */
    @Override
    public int deleteNoticesByIds(List<Integer> ids) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return noticeMapper.deleteByExample(example);
    }

    /**
     * 根据id更新公告信息
     *
     * @param notice 公告信息
     * @return rs
     */
    @Override
    public int updateNoticeById(Notice notice) {
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }

    /**
     * 新增公告
     *
     * @param notice 公告
     * @return rs
     */
    @Override
    public int saveNotice(Notice notice) {
        return noticeMapper.insert(notice);
    }
}
