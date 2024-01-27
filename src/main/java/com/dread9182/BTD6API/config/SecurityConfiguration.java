package com.dread9182.BTD6API.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((authorize) ->
						authorize.requestMatchers("/users/**")
								.permitAll()
								.requestMatchers("towers/update/**", "heroes/update/**", "bloons/update/**", "maps/update/**")
								.hasRole("ADMIN")
								.anyRequest()
								.authenticated())
				.sessionManagement((session) ->
						session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
	}
	
//	@Bean
//	public FilterRegistrationBean<TestAuthenticationFilter> testAuthenticationFilterFilterRegistrationBean() {
//		FilterRegistrationBean<TestAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
//		registrationBean.setName("testAuthenticationFilter");
//		registrationBean.setFilter(new TestAuthenticationFilter());
//		registrationBean.setOrder(1);
//		return registrationBean;
//	}


}
