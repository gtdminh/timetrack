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
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan({"com.fxrialab.timetrack.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public DataSource dataSource;

	@Autowired
	public UserService userService;

	@Autowired
	private CustomAuthenticationProvider customAuthProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.authenticationProvider(customAuthProvider);
//			.inMemoryAuthentication()
//				.withUser("gtdminh").password("password").authorities("ROLE_USER,ROLE_ADMIN");
//				.withUser("admin").password("password").roles("USER", "ADMIN");

	}

	@Bean
	public AbstractRememberMeServices getRememberService(){
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return new PersistentTokenBasedRememberMeServices("remember_me_key", userService, repo);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/home","/","/pages/**","/app/**","/auth/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().hasRole("USER")
			.and()
		.formLogin()
			.loginPage("/auth/login")
			.failureUrl("/auth/login?error")
			.defaultSuccessUrl("/app")
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/home")
			.permitAll()
			.and()
		.headers()
			.and()
		.rememberMe()
			.rememberMeServices(getRememberService())
		;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(false).ignoring().antMatchers("/resources/**");
	}
}
