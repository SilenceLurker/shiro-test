package xyz.silencelurker.test.shiro.config;

import org.hibernate.dialect.MySQLDialect;
import org.springframework.context.annotation.Configuration;

/**
 * 这玩意真的有必要吗？
 * 
 * @author Silence_Lurker
 */
@Configuration
public class MySQLConfig extends MySQLDialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
