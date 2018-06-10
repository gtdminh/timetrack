package com.fxrialab.timetrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.faces.mvc.JsfView;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = {"com.fxrialab.timetrack.controller","com.fxrialab.timetrack.security.controller"}, useDefaultFilters=false, includeFilters={@Filter(org.springframework.stereotype.Controller.class)})
public class WebConfig implements WebMvcConfigurer{

	
	@Bean
	public ViewResolver jspResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
		resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public ViewResolver jsfResolver(){
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setOrder(2);
		resolver.setViewClass(JsfView.class);
		resolver.setPrefix("/WEB-INF/admin/");
		resolver.setSuffix(".xhtml");
		
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/home").setViewName("index");
		registry.addRedirectViewController("/","/home");
	}
	
	
	
}
