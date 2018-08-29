package com.fxrialab.timetrack.config;

import com.fxrialab.timetrack.service.impl.security.CaptchaSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = {"com.fxrialab.timetrack.service"}, excludeFilters={@Filter(org.springframework.stereotype.Controller.class)})
@Import({SecurityConfig.class, PersistenceConfig.class, MailConfig.class})
public class AppRootConfig {

    @Bean
    public AppEventListeners eventListeners(){
        return new AppEventListeners();
    }

    @Bean(name = "captchaSettings")
    public CaptchaSettings getCaptchaSettings() {
        CaptchaSettings captchaSettings = new CaptchaSettings();
        captchaSettings.setSecret("6Le1RWwUAAAAAOt8G7RacJ_dkNzLIAOUJpsAjCMa");
        captchaSettings.setSite("localhost");
        return captchaSettings;
    }

    @Bean(name = "restTemplate")
    public RestOperations getRestTemplate() {
        return new RestTemplate();
    }
}
