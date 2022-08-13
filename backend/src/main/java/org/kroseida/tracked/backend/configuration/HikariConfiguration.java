package org.kroseida.tracked.backend.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Log4j2
@Configuration
public class HikariConfiguration {

  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.username}")
  private String username;
  @Value("${spring.datasource.password}")
  private String password;

  @Bean(destroyMethod = "close")
  public HikariDataSource dataSource(Environment env) {
    HikariConfig dataSourceConfig = new HikariConfig();
    dataSourceConfig.setJdbcUrl(url);
    dataSourceConfig.setUsername(username);
    dataSourceConfig.setPassword(password);

    return new HikariDataSource(dataSourceConfig);
  }

}
