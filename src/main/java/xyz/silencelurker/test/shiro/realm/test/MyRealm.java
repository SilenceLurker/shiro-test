package xyz.silencelurker.test.shiro.realm.test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.lang.util.ByteSource;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import jakarta.annotation.Resource;
import xyz.silencelurker.test.shiro.entity.UserInfo;
import xyz.silencelurker.test.shiro.service.ISysPermissionService;
import xyz.silencelurker.test.shiro.service.ISysRoleService;
import xyz.silencelurker.test.shiro.service.IUserInfoService;

public class MyRealm extends AuthorizingRealm {
    @Resource
    private IUserInfoService userInfoService;
    @Resource
    private ISysPermissionService sysPermissionService;
    @Resource
    private ISysRoleService sysRoleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        UserInfo userInfo = userInfoService.findByUsername(userName);

        if (userInfo == null) {
            throw new AuthenticationException("User not found");
        }

        return new SimpleAuthenticationInfo(
                userInfo.getUsername(),
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getSalt()), // 使用盐值
                getName());
    }

    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> permissions = new HashSet<>();
        var roles = getRolesByUserName(userName);
        var it = roles.iterator();

        while (it.hasNext()) {
            var perIt = sysPermissionService.getPermissionByRole(it.next()).iterator();
            while (perIt.hasNext()) {
                permissions.add(perIt.next().getName());
            }
        }
        return permissions;
    }

    private Set<String> getRolesByUserName(String userName) {
        Set<String> roles = new HashSet<>();
        var it = sysRoleService.getRoleByUsername(userName).iterator();

        while (it.hasNext()) {
            roles.add(it.next().getName());
        }
        return roles;
    }

    public void addUserInfo(UserInfo userInfo) {
        if (userInfo.getSalt() == null) {
            try {
                byte[] salt = new byte[16];
                SecureRandom.getInstanceStrong().nextBytes(salt);
                userInfo.setSalt(Base64.getEncoder().encodeToString(salt));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        HashedCredentialsMatcher matcher = (HashedCredentialsMatcher) getCredentialsMatcher();
        String hashedPassword = new SimpleHash(
                matcher.getHashAlgorithmName(),
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getSalt()),
                matcher.getHashIterations()).toHex();

        userInfo.setPassword(hashedPassword);
        userInfoService.addUserInfo(userInfo);
    }
}
