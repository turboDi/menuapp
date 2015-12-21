package org.turbodi.menuapp.data;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@Configuration
@ComponentScan
@Import(PersistenceConfig.class)
@PropertySource("classpath:test.properties")
public class PersistenceTestConfig {

    @Bean
    public Flyway flyway() {
        return new Flyway();
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

}
