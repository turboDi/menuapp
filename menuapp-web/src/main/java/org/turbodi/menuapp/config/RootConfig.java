package org.turbodi.menuapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.turbodi.menuapp.data.PersistenceConfig;

/**
 * @author Dmitriy Borisov
 * @created 12/20/2015
 */
@Configuration
@Import({SecurityConfig.class, PersistenceConfig.class})
@ComponentScan("org.turbodi.menuapp.web.service")
@PropertySource("classpath:application.properties")
public class RootConfig {
}
