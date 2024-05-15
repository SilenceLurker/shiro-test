package xyz.silencelurker.test.shiro.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import xyz.silencelurker.test.shiro.entity.UserInfo;
import xyz.silencelurker.test.shiro.realm.test.MyRealm;
import xyz.silencelurker.test.shiro.service.IUserInfoService;

/**
 * @author Silence_Lurker
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Resource
    private IUserInfoService userInfoService;
    @Resource
    private MyRealm realm;

    /**
     * 注册
     * 
     * @param newUser
     * @return
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserInfo newUser) throws NoSuchAlgorithmException {
        realm.addUserInfo(newUser);
        return ResponseEntity.ok(userInfoService.findByUsername(newUser.getUsername()));

    }
}
