package xyz.silencelurker.test.shiro.service.impl;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import xyz.silencelurker.test.shiro.entity.UserInfo;
import xyz.silencelurker.test.shiro.repository.UserInfoRepository;
import xyz.silencelurker.test.shiro.service.IUserInfoService;

/**
 * @author Silence_Lurker
 */
@Slf4j
@Service
public class IUserInfoServiceImpl implements IUserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUsername(String username) {
        log.info(username);
        return userInfoRepository.findByUsername(username);
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

}
