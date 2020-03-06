package com.gongsir.wxapp.service;

import com.gongsir.wxapp.model.Admin;

import java.sql.SQLException;
import java.util.List;

/**
 * @author gongsir
 * @date 2020/2/15 12:58
 * 编码不要畏惧变化，要拥抱变化
 */
public interface AdminService {
    /**
     * 注册管理员
     * @param admin 管理员基本信息
     * @return 注册结果
     */
    int saveAdmin(Admin admin) throws SQLException;

    /**
     * 登录查找
     * @param admId 管理员账号
     * @return 管理员信息
     */
    Admin selectAdminByAdmId(String admId);

    /**
     * 获取管理员信息
     * @param username 账号
     * @param status 状态
     * @param limit 每页数量
     * @param page 页码
     * @return list
     */
    List<Admin> getAllAdmins(String username,
                             String status,
                             int limit,
                             int page);
    Long countAllAdmins(String username, String status);

    /**
     * 删除单个用户
     * @param id 用户id
     * @return result
     */
    int deleteAdminById(int id);

    /**
     * 批量删除
     * @param ids id集合
     * @return result
     */
    int deleteAdminsByIds(List<Integer> ids);

    int updateAdminByAdmId(Admin admin);
}
