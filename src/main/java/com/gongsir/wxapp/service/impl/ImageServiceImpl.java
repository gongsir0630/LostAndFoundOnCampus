package com.gongsir.wxapp.service.impl;

import com.gongsir.wxapp.mapper.ImageMapper;
import com.gongsir.wxapp.model.Image;
import com.gongsir.wxapp.model.ImageExample;
import com.gongsir.wxapp.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gongsir
 * @date 2020/2/22 14:57
 * 编码不要畏惧变化，要拥抱变化
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageMapper imageMapper;
    /**
     * 后台获取所有公告信息
     *
     * @param status 公告状态
     * @param page   页码
     * @param limit  每页显示数量
     * @return list
     */
    @Override
    public List<Image> getAllImages(String status, int page, int limit) {
        ImageExample example = new ImageExample();
        ImageExample.Criteria criteria = example.createCriteria();
        if (null != status && !"".equalsIgnoreCase(status)){
            criteria.andImgStatusEqualTo(status);
        }
        page = Math.max(page,1);
        example.setLimit(limit);
        example.setOffset((page-1)*limit);
        return imageMapper.selectByExample(example);
    }

    @Override
    public long countAllImages(String status) {
        ImageExample example = new ImageExample();
        ImageExample.Criteria criteria = example.createCriteria();
        if (null != status && !"".equalsIgnoreCase(status)){
            criteria.andImgStatusEqualTo(status);
        }
        return imageMapper.countByExample(example);
    }

    /**
     * 删除单条公告
     *
     * @param id id
     * @return rs
     */
    @Override
    public int deleteImageById(int id) {
        return imageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除公告
     *
     * @param ids id集合
     * @return rs
     */
    @Override
    public int deleteImagesByIds(List<Integer> ids) {
        ImageExample example = new ImageExample();
        ImageExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return imageMapper.deleteByExample(example);
    }

    /**
     * 根据id更新公告信息
     *
     * @param image 公告信息
     * @return rs
     */
    @Override
    public int updateImageById(Image image) {
        return imageMapper.updateByPrimaryKeySelective(image);
    }

    /**
     * 新增公告
     *
     * @param image 公告
     * @return rs
     */
    @Override
    public int saveImage(Image image) {
        return imageMapper.insert(image);
    }
}
