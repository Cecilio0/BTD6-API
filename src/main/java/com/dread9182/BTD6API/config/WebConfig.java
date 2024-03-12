package com.dread9182.BTD6API.config;

import com.dread9182.BTD6API.interceptor.bloon.BloonBodyRequestHandler;
import com.dread9182.BTD6API.interceptor.bloon.BloonTypeRequestHandler;
import com.dread9182.BTD6API.interceptor.hero.HeroBodyRequestHandler;
import com.dread9182.BTD6API.interceptor.hero.HeroUnlockRequestHandler;
import com.dread9182.BTD6API.interceptor.map.MapPostRequestHandler;
import com.dread9182.BTD6API.interceptor.map.MapDifficultyRequestHandler;
import com.dread9182.BTD6API.interceptor.map.MapPutRequestHandler;
import com.dread9182.BTD6API.interceptor.tower.TowerPostRequestHandler;
import com.dread9182.BTD6API.interceptor.tower.TowerPutRequestHandler;
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
		registry.addInterceptor(new TowerPostRequestHandler()).addPathPatterns("/towers/save").order(11);
		registry.addInterceptor(new TowerPutRequestHandler()).addPathPatterns("/towers/update/**").order(12);
		
		// Heroes
		registry.addInterceptor(new HeroUnlockRequestHandler()).addPathPatterns("/heroes/how/**").order(15);
		registry.addInterceptor(new HeroBodyRequestHandler())
				.addPathPatterns(
						"/heroes/save",
						"/heroes/update/**")
				.order(16);
		
		// Bloons
		registry.addInterceptor(new BloonTypeRequestHandler()).addPathPatterns("/bloons/type/**").order(20);
		registry.addInterceptor(new BloonBodyRequestHandler())
				.addPathPatterns(
						"/bloons/save",
						"/bloons/update/**")
				.order(21);
		
		// Maps
		registry.addInterceptor(new MapDifficultyRequestHandler()).addPathPatterns("/maps/difficulty/**").order(25);
		registry.addInterceptor(new MapPostRequestHandler()).addPathPatterns("/maps/save").order(26);
		registry.addInterceptor(new MapPutRequestHandler()).addPathPatterns("/maps/update/**").order(27);
	}
}
