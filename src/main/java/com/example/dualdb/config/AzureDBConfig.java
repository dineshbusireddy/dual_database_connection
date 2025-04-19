package com.example.dualdb.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/*@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.preg.repository.azure",
        entityManagerFactoryRef = "azureEntityManagerFactory",
        transactionManagerRef = "azureTransactionManager"
)
@EnableConfigurationProperties*/
public class AzureDBConfig {

    @Primary
    @Bean
    //@ConfigurationProperties(prefix = "spring.datasource.azure")
    public DataSource azureDataSource() {
        // Manually set the DataSource properties
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1")  // Manually set the jdbcUrl
                .username("sa")  // Manually set the username
                .driverClassName("org.h2.Driver")  // Manually set the driver class name
                .build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean azureEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(azureDataSource())
                .packages("com.example.preg.model.azure")
                .persistenceUnit("azure")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager azureTransactionManager(
            @Qualifier("azureEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
