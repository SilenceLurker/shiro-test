package xyz.silencelurker.test.shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.MatcherConfigurer;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.test.shiro.entity.SysPermission;
import xyz.silencelurker.test.shiro.entity.SysRole;
import xyz.silencelurker.test.shiro.repository.SysPermissionRepository;
import xyz.silencelurker.test.shiro.repository.SysRoleRepository;
import xyz.silencelurker.test.shiro.repository.UserInfoRepository;
import xyz.silencelurker.test.shiro.service.ISysPermissionService;

/**
 * @author Silence_Lurker
 */
@Service
public class ISysPermissionServiceImpl implements ISysPermissionService {

    @Resource
    private SysPermissionRepository sysPermissionRepository;
    @Resource
    private SysRoleRepository sysRoleRepository;
    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public List<SysPermission> getAllPermission() {
        return sysPermissionRepository.findAll();
    }

    @Override
    public List<SysPermission> getPermissionByRole(String role) {
        var example = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("name",
                GenericPropertyMatchers.storeDefaultMatching());
        var exampleRole = new SysRole();
        exampleRole.setName(role);
        var roles = sysRoleRepository.findAll(Example.of(exampleRole, example));

        var it = roles.iterator();

        var ids = new ArrayList<Long>();
        while (it.hasNext()) {
            var perIt = it.next().getPermissions().iterator();
            while (perIt.hasNext()) {
                ids.add(perIt.next().getId());
            }
        }

        return sysPermissionRepository.findAllById(ids);

    }

}
