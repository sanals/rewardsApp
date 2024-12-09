package com.rewards.app.security;

import javax.sql.DataSource;

import com.rewards.app.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService service;
	
	@Autowired
	private DataSource dataSource;// check if we need to add this as a @bean

	@Autowired
	private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http
    		.csrf(c->c.disable())
    		.authorizeHttpRequests((auth)->{ 
	            auth
	            .requestMatchers("users/createUser6/**").permitAll()
				.requestMatchers("users/login/**").permitAll()
				.requestMatchers("test/**").permitAll()
	            .anyRequest().authenticated();
//	            .permitAll();
            })
//    		.formLogin(Customizer.withDefaults())
    		.httpBasic(Customizer.withDefaults())
    		.headers(header->header.frameOptions(frameOptions->frameOptions.sameOrigin()))//to allow h2 database page to be loaded
    		.sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    		;
        return http.build();
    }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
    
    @Bean
    public AuthenticationProvider provider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(service);
//    	provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
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
