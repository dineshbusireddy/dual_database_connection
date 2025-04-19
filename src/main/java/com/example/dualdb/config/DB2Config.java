package com.example.dualdb.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DB2Config {
    private final DataSourcesProperties dataSourceProperties;

    @Bean
    public DataSource db2DataSource() {
        DataSourcesProperties.SingleDataSourceProperties db2Prop = dataSourceProperties.getDb2();
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(db2Prop.getUrl());
        dataSource.setUsername(db2Prop.getUsername());
        dataSource.setPassword(db2Prop.getPassword());
        dataSource.setDriverClassName(db2Prop.getDriverClassName());
        return dataSource;
    }

    @Bean
    public JdbcTemplate db2JdbcTemplate(@Qualifier("db2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ApplicationRunner initDatabases(
            @Qualifier("db2DataSource") DataSource db2DS) {
        return args -> {
            runSqlScripts(db2DS, "schema-db2.sql", "data-db2.sql");
        };
    }

    private void runSqlScripts(DataSource dataSource, String... scriptPaths) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        for (String path : scriptPaths) {
            populator.addScript(new ClassPathResource(path));
        }
        populator.execute(dataSource);
    }

}

