package com.wildcodeschool.myProjectWithSecurity.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((authz) -> {
			try {
				authz
				.requestMatchers("/avengers/assemble/**").hasRole("CHAMPION")
				.requestMatchers("/secret-base/**").hasAuthority("DIRECTOR")
				.requestMatchers("/", "/swagger-ui/**").permitAll()
				.anyRequest().authenticated
				().and().formLogin()
				.and().httpBasic();
			} catch (Exception e) {

				e.printStackTrace();

			}
		});
		return http.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		UserDetails user = User
				.withUsername("Steve")
				.password(encoder.encode("motdepasse"))
				.roles("CHAMPION")
				.build();
		
		UserDetails admin = User
				.withUsername("Nick")
				.password(encoder.encode("flerken"))
				.roles("DIRECTOR")
				.build();
		
		return new InMemoryUserDetailsManager(List.of(user,admin));
				
	}
}
