package com.example.dualdb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourcesProperties {

    private SingleDataSourceProperties azure;
    private SingleDataSourceProperties db2;

    @Data
    public static class SingleDataSourceProperties {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
    }
}
