package xyz.silencelurker.test.shiro.service;

import xyz.silencelurker.test.shiro.entity.UserInfo;

/**
 * @author Silence_Lurker
 */
public interface IUserInfoService {
    /**
     * 根据用户名查找用户
     * 
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);

    /**
     * 添加用户
     * 
     * @param userInfo
     */
    void addUserInfo(UserInfo userInfo);
}
