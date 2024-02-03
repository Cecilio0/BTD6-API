package com.dread9182.BTD6API.filter;

import com.dread9182.BTD6API.config.CachedBodyHttpServletRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestWrapperFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(request);
		filterChain.doFilter(cachedRequest, response);
	}
}
