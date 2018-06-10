package com.fxrialab.timetrack.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.fxrialab.timetrack.services", "com.fxrialab.timetrack.security.services"}, excludeFilters={@Filter(org.springframework.stereotype.Controller.class)})
public class AppRootConfig {
	
}
