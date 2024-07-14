package com.rewards.app.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService service;
	
	@Autowired
	private DataSource dataSource;// check if we need to add this as a @bean

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http
    		.csrf(c->c.disable())
    		.authorizeHttpRequests((auth)->{ 
	            auth
	            .requestMatchers("/h2-console/**").permitAll()
	            .anyRequest().authenticated();
//	            .permitAll();
            })
//    		.formLogin(Customizer.withDefaults())
    		.httpBasic(Customizer.withDefaults())
    		.headers(header->header.frameOptions(frameOptions->frameOptions.sameOrigin()))//to allow h2 database page to be loaded
    		.sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    		;
        return http.build();
    }
    
    @Bean
    public AuthenticationProvider provider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(service);
    	provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    	return provider;
    }
    
//    @Bean
//    public UserDetailsService userDetailsService() {
//    	UserDetails user = User.withDefaultPasswordEncoder()
//    			.username("user")
//    			.password("userp")
//    			.roles("USER")
//    			.build();
//    	
//    	UserDetails admin = User.withUsername("admin")
//    			.password("{noop}adminp")
//    			.roles("ADMIN")
//    			.build();
//    	
////    	JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
////    	userDetailsManager.createUser(user);
////    	userDetailsManager.createUser(admin);
////    	return userDetailsManager;
//    	return new InMemoryUserDetailsManager(user, admin);
//    } 

}