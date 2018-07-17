package com.theatro.api.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.biz")
    public DataSourceProperties bizDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "bizDatasource")
    @Primary
    @ConfigurationProperties("app.datasource.biz")
    public DataSource bizDataSource() {
        return bizDataSourceProperties().initializeDataSourceBuilder().build();
    }


}
