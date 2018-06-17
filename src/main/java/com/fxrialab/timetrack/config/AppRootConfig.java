package com.fxrialab.timetrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.fxrialab.timetrack.services", "com.fxrialab.timetrack.security.services"}, excludeFilters={@Filter(org.springframework.stereotype.Controller.class)})
@Import({SecurityConfig.class, PersistenceConfig.class})
public class AppRootConfig {

    @Bean
    public AppEventListeners eventListeners(){
        return new AppEventListeners();
    }
}
