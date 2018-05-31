package com.fxrialab.timetrack.security.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController{
	private static Logger log = Logger.getLogger(AuthController.class.getName());
	
	@RequestMapping("/app/login")
	public String login() {
		log.info("login");
		return "login";
	}
	
	@RequestMapping("/app/logout")
	public String logout() {
		return "logout";
	}
	
	@RequestMapping("/app/doLogin")
	public void doLogin(Model m){
		
	}
}
