package xyz.silencelurker.test.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import xyz.silencelurker.test.shiro.realm.test.MyRealm;

/**
 * @author Silence_Lurker
 */
@SpringBootTest
class ShiroApplicationTests {

	@Test
	void contextLoads() {
	}

	SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

	// @BeforeAll
	@BeforeEach
	public void addTestUser() {
		simpleAccountRealm.addAccount("silence", "123456");
	}

	@Test
	void simpleAuthenticationTest() {
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(simpleAccountRealm);

		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("silence", "123456");
		subject.login(token);

		System.out.println("isAuthenticated:" + subject.isAuthenticated());

		subject.logout();

		System.out.println("isAuthenticated:" + subject.isAuthenticated());
	}

	/**
	 * 通过Realm进行安全管理认证
	 * 
	 * 认证测试
	 */
	@Test
	void authentication() {
		MyRealm realm = new MyRealm();

		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		securityManager.setRealm(realm);

		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("mark", "123456");
		subject.login(token);

		System.out.println("isAuthenticated:" + subject.isAuthenticated());
		subject.checkRoles("admin", "user");
		subject.checkPermission("user:add");
	}

	@Test
	void listTest() {
		List<String> testList = new ArrayList<>();

		for (String s : testList) {
			System.out.println(s);
		}

	}

}
