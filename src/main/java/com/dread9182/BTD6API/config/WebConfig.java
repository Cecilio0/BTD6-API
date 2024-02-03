package com.dread9182.BTD6API.config;

import com.dread9182.BTD6API.interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// General
		
		// Users
		registry.addInterceptor(new UserRegisterRequestHandler()).addPathPatterns("/users/register").order(5);
		registry.addInterceptor(new UserAuthenticationRequestHandler()).addPathPatterns("/users/authenticate").order(6);
		
		// Towers
		registry.addInterceptor(new TowerTypeRequestHandler()).addPathPatterns("/towers/type/**").order(10);
		registry
				.addInterceptor(new TowerBodyRequestHandler())
				.addPathPatterns(
						"/towers/update/**",
						"/towers/save")
				.order(11);
	}
}
