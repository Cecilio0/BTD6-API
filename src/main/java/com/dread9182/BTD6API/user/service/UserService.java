package com.dread9182.BTD6API.user.service;

import com.dread9182.BTD6API.config.JwtService;
import com.dread9182.BTD6API.user.*;
import com.dread9182.BTD6API.user.model.Role;
import com.dread9182.BTD6API.user.model.User;
import com.dread9182.BTD6API.user.model.request.UserAuthenticationRequest;
import com.dread9182.BTD6API.user.model.request.UserAuthenticationResponse;
import com.dread9182.BTD6API.user.model.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
	
	@Autowired
	private final IUserRepository ur;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	@Autowired
	private final JwtService jwtService;
	@Autowired
	private final AuthenticationManager authenticationManager;
	
	@Override
	public UserAuthenticationResponse register(UserRegisterRequest request) {
		// todo implement interceptor for bad UserRegisterRequest and unique emails
		User user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.ROLE_USER)
				.build();
		
		ur.save(user);
		
		String jwt = jwtService.generateToken(user);
		
		return UserAuthenticationResponse.builder()
				.token(jwt)
				.build();
	}
	
	@Override
	public UserAuthenticationResponse authenticate(UserAuthenticationRequest request) {
		// todo implement interceptor for checking if user even sent a username and a password
		// todo implement exception for bad user data
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
				)
		);
		
		User user = ur.findByEmail(request.getEmail()).orElse(null);
		
		String jwt = jwtService.generateToken(user);
		
		return UserAuthenticationResponse.builder()
				.token(jwt)
				.build();
	}
}
