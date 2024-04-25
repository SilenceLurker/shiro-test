package xyz.silencelurker.test.shiro.service.impl;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.test.shiro.entity.UserInfo;
import xyz.silencelurker.test.shiro.repository.UserInfoRepository;
import xyz.silencelurker.test.shiro.service.IUserInfoService;

/**
 * @author Silence_Lurker
 */
@Service
public class IUserInfoServiceImpl implements IUserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }

}
