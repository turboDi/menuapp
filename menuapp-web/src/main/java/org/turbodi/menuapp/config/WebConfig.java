package org.turbodi.menuapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Dmitriy Borisov
 * @created 12/18/2015
 */
@EnableWebMvc
@ComponentScan("org.turbodi.menuapp.web.controller")
@Configuration
public class WebConfig {
}
