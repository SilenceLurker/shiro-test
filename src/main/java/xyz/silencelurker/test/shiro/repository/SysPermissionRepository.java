package xyz.silencelurker.test.shiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.silencelurker.test.shiro.entity.SysPermission;

/**
 * @author Silence_Lurker
 */
public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {

}
