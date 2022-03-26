package edu.fra.uas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.fra.uas.service.user.UserService;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	// Password Encoder this Method can be used to Encode Password
	@Bean
	BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	// This Method will be used to encode the Password
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());

		return auth;
	}

	// Authentication check this will be used to check the authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());

	}

	// Allow request or not.We define the right,who has which rights to see the
	// which Mapping
	// /logout is deleting the Cookie("Jsessionid") so the User can not use the
	// Session and enter the Mapping manual to go this Mapping without the login
	// again
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/registration**", "/css/**", "/img/**", "/listProject**").permitAll()
				.antMatchers("/js/**", "/showNewProjectForm/**", "/new_Project/**", "/update_project/**",
						"/showFormForUpdate/**", "/view_project/upload**", "/upload**", "/deleteProject/**")
				.hasAuthority("ROLE_ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login")
				.permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll().and().rememberMe();
		// disable the CSRF for simplify interactions between a client and the server.
		http.csrf().disable();
	}

}
