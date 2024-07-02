package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import spring.MemberDao;

@Configuration
@Profile("real,test")
public class DataSourceJndiConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource);
    }

    @Configuration
    @Profile("dev")
    public static class DsDevConfig {
        @Bean(destroyMethod = "close")
        public DataSource dataSource() {
            DataSource ds = new DataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
            ds.setUsername("spring5");
            ds.setPassword("spring5");
            ds.setInitialSize(2);
            ds.setMaxActive(10);
            ds.setTestWhileIdle(true);
            ds.setMinEvictableIdleTimeMillis(60000 * 3);
            ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
            return ds;
        }
    }

}
