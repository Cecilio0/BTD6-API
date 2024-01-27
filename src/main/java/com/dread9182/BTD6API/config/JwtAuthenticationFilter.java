package com.dread9182.BTD6API.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	
	// Defines explicitly which routes should not be filtered by this filter
	
	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
	) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		
		// If token is incorrectly formatted or is nonexistent do an early return
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			//todo implement exception to return error message not just httpStatus
			//throw new UserNotAuthenticatedException("User has provided no authentication or it is incorrectly formatted");
			return;
		}
		
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUsername(jwt);
		
		// User is not authenticated yet
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
			
			// Check if user exists in database
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			
			// Set user as authenticated
			if (jwtService.isTokenValid(jwt, userDetails)){
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userDetails,
					null,
					userDetails.getAuthorities()
				);
				authToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
				);
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
