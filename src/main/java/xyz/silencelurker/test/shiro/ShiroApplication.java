package xyz.silencelurker.test.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Silence_Lurker
 */
@ComponentScan(basePackages = { "xyz.silencelurker.test.shiro" })
@SpringBootApplication
public class ShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroApplication.class, args);
	}

}
