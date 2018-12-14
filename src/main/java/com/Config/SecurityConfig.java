package com.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	



	private static final String[] ADMIN_MATCHERS = {
			"/current_schedule",
			"/input_schedule",
			"/historical_schedule",
			"/schedule_detail",
			"/admin_booking",
			"/historical_booking",
			"/booking_detail",

			"/bus_management",
			"/bus_update",
			"/location_management",
			"/report", "/sReport",
			"/create_user",
			"/departure_time",
			"/departure_date",
			"/pricemng",
			"/ticketmng"
    };
	private static final String[] USER_MATCHERS = {

		"/customer_home",
			"/customer/**",
		"/request_booking",
		"/booking_history",
			"/get_hash"
		
   
    };
	private static final String[] STUDENT_MATCHERS = {

		"/student/**",

    };
	private static final String[] PAYMENT = {

		"/push_back_notification",

    };

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.authorizeRequests()
        .antMatchers(USER_MATCHERS).access("hasRole('ROLE_CUSTOMER')").and().formLogin().loginPage("/login").failureUrl("/login?error")
		   .usernameParameter("username")
		   .passwordParameter("password")
		   .and().logout().logoutSuccessUrl("/login?logout")
		   .and().csrf()
		   .and().exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests()
				.antMatchers(ADMIN_MATCHERS).access("hasRole('ROLE_ADMIN')").and().formLogin()
				.loginPage("/login").failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout().logoutSuccessUrl("/login?logout")
				.and().csrf()
				.and().exceptionHandling().accessDeniedPage("/403");


		
		http.authorizeRequests()
                .antMatchers(STUDENT_MATCHERS).access("hasRole('ROLE_STUDENT')")
                .and().formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutSuccessUrl("/login?logout")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403");
        http.csrf().ignoringAntMatchers(PAYMENT);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}




	
	
	
	
	
	
	
	
	
	
	
	
