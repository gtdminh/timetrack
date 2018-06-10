package com.fxrialab.timetrack.config;

import com.fxrialab.timetrack.security.CustomAuthenticationProvider;
import com.fxrialab.timetrack.security.persistence.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan({"com.fxrialab.timetrack.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public DataSource dataSource;

	@Autowired
	public UserService userService;

	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new Pbkdf2PasswordEncoder("timetrack_secret");

		return encoder;
	}

	@Autowired
	private CustomAuthenticationProvider customAuthProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.authenticationProvider(customAuthProvider);
//			.inMemoryAuthentication()
//				.withUser("user").password("password").roles("USER").and()
//				.withUser("admin").password("password").roles("USER", "ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/signup","/about","/home*","/").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.failureForwardUrl("/login?error").permitAll()
			//.successForwardUrl("/account")
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/home")
			.permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true).ignoring().antMatchers("/resources/**");
	}
}
