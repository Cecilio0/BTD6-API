package com.dread9182.BTD6API.config;

import com.dread9182.BTD6API.interceptor.UserAuthenticationRequestHandler;
import com.dread9182.BTD6API.interceptor.UserRegisterRequestHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserRegisterRequestHandler()).addPathPatterns("/users/register");
		registry.addInterceptor(new UserAuthenticationRequestHandler()).addPathPatterns("/users/authenticate");
	}
}
