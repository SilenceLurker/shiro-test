package xyz.silencelurker.test.shiro.service.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import jakarta.annotation.Resource;
import xyz.silencelurker.test.shiro.entity.SysPermission;
import xyz.silencelurker.test.shiro.entity.SysRole;
import xyz.silencelurker.test.shiro.entity.UserInfo;
import xyz.silencelurker.test.shiro.service.IUserInfoService;

/**
 * @author Silence_Lurker
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private IUserInfoService userInfoService;

    {
        this.setName("Test Shiro Realm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        for (SysRole role : userInfo.getRoles()) {
            simpleAuthorizationInfo.addRole(role.getName());
            for (SysPermission permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        System.out.println(token.getPrincipal());

        UserInfo userInfo = userInfoService.findByUsername(username);

        if (null == userInfo) {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),
                // ByteSource.Util.bytes(userInfo.getSalt()),
                getName());

        return simpleAuthenticationInfo;
    }

}
