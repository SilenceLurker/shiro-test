package xyz.silencelurker.test.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import xyz.silencelurker.test.shiro.entity.SysRole;
import xyz.silencelurker.test.shiro.service.ISysRoleService;

/**
 * @author Silence_Lurker
 */
@RequiresRoles("admin")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private ISysRoleService sysRoleService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewRole(@RequestBody SysRole newRole) {

        return ResponseEntity.ok("添加角色成功");
    }
}
