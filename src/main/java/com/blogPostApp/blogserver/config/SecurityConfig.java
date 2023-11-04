package com.blogPostApp.blogserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	
	

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        
	http.
	csrf().disable()
	.authorizeHttpRequests()
	.anyRequest()
	.authenticated()
	.and()
	.httpBasic();
	
	
	}
	
	
	
	
}
