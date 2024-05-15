package xyz.silencelurker.test.shiro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.test.shiro.entity.SysRole;
import xyz.silencelurker.test.shiro.repository.SysRoleRepository;
import xyz.silencelurker.test.shiro.repository.UserInfoRepository;
import xyz.silencelurker.test.shiro.service.ISysRoleService;

/**
 * @author Silence_Lurker
 */
@Service
public class ISysRoleServiceImpl implements ISysRoleService {

    @Resource
    private SysRoleRepository sysRoleRepository;
    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public List<SysRole> getAllRole() {
        return sysRoleRepository.findAll();
    }

    @Override
    public List<SysRole> getRoleByUsername(String username) {
        var user = userInfoRepository.findByUsername(username);
        return user.getRoles();
    }

    @Override
    public void addRole(SysRole role) {
        sysRoleRepository.save(role);
    }

}
