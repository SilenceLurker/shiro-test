package xyz.silencelurker.test.shiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.silencelurker.test.shiro.entity.UserInfo;

/**
 * @author Silence_Lurker
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    /**
     * 通过用户名查找用户
     * 
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);

}
