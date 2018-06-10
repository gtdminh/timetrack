package com.fxrialab.timetrack.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    private static Logger log = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/login")
    public String login(Model model) {
        log.info("login");
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }

}
