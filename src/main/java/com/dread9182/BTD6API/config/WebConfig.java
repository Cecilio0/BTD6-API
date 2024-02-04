package com.dread9182.BTD6API.config;

import com.dread9182.BTD6API.interceptor.hero.HeroBodyRequestHandler;
import com.dread9182.BTD6API.interceptor.hero.HeroUnlockRequestHandler;
import com.dread9182.BTD6API.interceptor.tower.TowerBodyRequestHandler;
import com.dread9182.BTD6API.interceptor.tower.TowerTypeRequestHandler;
import com.dread9182.BTD6API.interceptor.user.UserAuthenticationRequestHandler;
import com.dread9182.BTD6API.interceptor.user.UserRegisterRequestHandler;
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
		registry.addInterceptor(new TowerBodyRequestHandler())
				.addPathPatterns(
						"/towers/update/**",
						"/towers/save")
				.order(11);
		
		// Heroes
		registry.addInterceptor(new HeroUnlockRequestHandler()).addPathPatterns("/heroes/how/**").order(15);
		registry.addInterceptor(new HeroBodyRequestHandler())
				.addPathPatterns(
						"/heroes/save",
						"/heroes/update/**")
				.order(16);
		
		// Bloons
		
		// Maps
	}
}
