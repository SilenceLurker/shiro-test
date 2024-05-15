package xyz.silencelurker.test.shiro.service;

import java.util.List;

import xyz.silencelurker.test.shiro.entity.SysRole;

/**
 * @author Silence_Lurker
 */
public interface ISysRoleService {
    /**
     * 获取所有角色
     * 
     * @return
     */
    List<SysRole> getAllRole();

    /**
     * 根据用户名获取角色
     * 
     * @param username
     * @return
     */
    List<SysRole> getRoleByUsername(String username);

    /**
     * 添加角色
     * 
     * @param role
     */
    void addRole(SysRole role);
}
