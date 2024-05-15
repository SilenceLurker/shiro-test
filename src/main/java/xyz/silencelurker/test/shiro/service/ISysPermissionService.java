package xyz.silencelurker.test.shiro.service;

import java.util.List;

import xyz.silencelurker.test.shiro.entity.SysPermission;

/**
 * @author Silence_Lurker
 */
public interface ISysPermissionService {
    /**
     * 获取所有权限
     * 
     * @return
     */
    List<SysPermission> getAllPermission();

    /**
     * 根据用户名获取权限
     * 
     * @param role
     * @return
     */
    List<SysPermission> getPermissionByRole(String role);
}
