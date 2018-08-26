package com.fxrialab.timetrack.service.impl.security;

import org.springframework.stereotype.Component;

@Component
public class CaptchaSettings {

    private String site;
    private String secret;

    // standard getters and setters
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}