package xyz.silencelurker.test.shiro.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import xyz.silencelurker.test.shiro.service.IUserInfoService;

/**
 * @author Silence_Lurker
 */
@Slf4j
@RestController
@RequestMapping("/")
public class TestController {
    @RequestMapping({ "/", "/index" })
    public String index() {
        return "/index";
    }

    @Resource
    private IUserInfoService userInfoService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map, @RequestParam String username,
            @RequestParam String password)
            throws Exception {
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";

        try {
            Subject subject = SecurityUtils.getSubject();

            log.info(username + password);
            log.info(userInfoService.findByUsername(username).getPassword());

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            subject.login(token);

            System.out.println(subject.getPrincipal());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> " + exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理

        // Subject subject = org.apache.shiro.SecurityUtils.getSubject();

        System.out.println("msg=" + new ObjectMapper().writeValueAsString(msg));

        // System.out.println("subject=" + subject);

        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("------没有权限-------");
        return "403";
    }
}
