package com.gongsir.wxapp.service.impl;

import com.gongsir.wxapp.mapper.AdminMapper;
import com.gongsir.wxapp.model.Admin;
import com.gongsir.wxapp.model.AdminExample;
import com.gongsir.wxapp.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gongsir
 * @date 2020/2/15 13:10
 * 编码不要畏惧变化，要拥抱变化
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    /**
     * 注册管理员
     *
     * @param admin 管理员基本信息
     * @return 注册结果
     */
    @Override
    public int saveAdmin(Admin admin) {
        return adminMapper.insertSelective(admin);
    }

    /**
     * 登录查找
     *
     * @param admId 管理员账号
     * @return 管理员信息
     */
    @Override
    public Admin selectAdminByAdmId(String admId) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdmIdEqualTo(admId);
        List<Admin> admins = adminMapper.selectByExample(example);
        if (!admins.isEmpty()){
            return admins.get(0);
        }
        return null;
    }

    /**
     * 获取管理员信息
     *
     * @param username 账号
     * @param status   状态
     * @param limit    每页数量
     * @param page     页码
     * @return list
     */
    @Override
    public List<Admin> getAllAdmins(String username, String status, int limit, int page) {
        page = page > 0 ? page : 1;
        int offset = (page-1)*limit;
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if (null != username && !"".equalsIgnoreCase(username.trim())){
            criteria.andAdmIdLike("%"+username+"%");
        }
        if (null != status && !"".equalsIgnoreCase(status.trim())){
            criteria.andStatusEqualTo(status);
        }
        example.setLimit(limit);
        example.setOffset(offset);
        return adminMapper.selectByExample(example);
    }

    @Override
    public Long countAllAdmins(String username, String status) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if (null != username && !"".equalsIgnoreCase(username.trim())){
            criteria.andAdmIdLike("%"+username+"%");
        }
        if (null != status && !"".equalsIgnoreCase(status.trim())){
            criteria.andStatusEqualTo(status);
        }
        return adminMapper.countByExample(example);
    }

    /**
     * 删除单个用户
     *
     * @param id 用户id
     * @return result
     */
    @Override
    public int deleteAdminById(int id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return result
     */
    @Override
    public int deleteAdminsByIds(List<Integer> ids) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return adminMapper.deleteByExample(example);
    }

    @Override
    public int updateAdminByAdmId(Admin admin) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdmIdEqualTo(admin.getAdmId());
        return adminMapper.updateByExampleSelective(admin,example);
    }
}
