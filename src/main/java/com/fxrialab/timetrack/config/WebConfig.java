package com.fxrialab.timetrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.mvc.JsfView;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan("com.fxrialab.timetrack")
public class WebConfig implements WebMvcConfigurer{

	
	@Bean
	public ViewResolver jspResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver("/WEB-INF/views", ".jsp");
		resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public ViewResolver jsfResolver(){
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setOrder(2);
		resolver.setViewClass(JsfView.class);
		resolver.setPrefix("/WEB-INF/views");
		resolver.setSuffix(".xhtml");
		
		return resolver;
	}
	
	
}
