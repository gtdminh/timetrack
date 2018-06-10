package com.fxrialab.timetrack.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class RootContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	public RootContextInitializer() {
		super();
	}

	@Override
	public void initialize(ConfigurableApplicationContext appContext) {
		ConfigurableEnvironment env = appContext.getEnvironment();
		
		env.setActiveProfiles("dev");
	}

}
