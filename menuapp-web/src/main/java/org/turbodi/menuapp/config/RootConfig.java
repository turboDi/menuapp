package org.turbodi.menuapp.config;

import org.springframework.context.annotation.*;
import org.turbodi.menuapp.data.PersistenceConfig;

/**
 * @author Dmitriy Borisov
 * @created 12/20/2015
 */
@Configuration
@Import({SecurityConfig.class, PersistenceConfig.class})
@ComponentScan("org.turbodi.menuapp.web.service")
public class RootConfig {

    @Configuration
    @Profile("default")
    @PropertySource("classpath:application.properties")
    public static class PostgreSQLConfiguration {
    }

    @Configuration
    @Profile("h2")
    @PropertySource("classpath:application-h2.properties")
    public static class H2Configuration {
    }

}
