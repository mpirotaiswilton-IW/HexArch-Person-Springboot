package com.max_pw_iw.person_subscriber.adapter.out.persistence.repositories.people;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@EnableJpaRepositories("com.max_pw_iw.person_subscriber.adapter.out.persistence.repositories.people")
public class PeopleRepoConfig {
    
    @Autowired
    private Environment environment;

    private String propPrefix = "spring.datasource.";

    @Bean
    @Primary
    @Qualifier("peopleDataSource")
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(environment.getProperty(propPrefix + "driver-class-name"));
        dataSourceBuilder.url(environment.getProperty(propPrefix + "url"));
        dataSourceBuilder.username(environment.getProperty(propPrefix + "username"));
        dataSourceBuilder.password(environment.getProperty(propPrefix + "password"));
        return dataSourceBuilder.build();
    }

    @Bean
    @Primary
    NamedParameterJdbcOperations jdbcOperations( @Qualifier("peopleDataSource") DataSource sqlServerDs) {
        return new NamedParameterJdbcTemplate(sqlServerDs);
  }

}
