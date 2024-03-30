package com.dread9182.BTD6API.user.service;

import com.dread9182.BTD6API.config.JwtService;
import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.user.*;
import com.dread9182.BTD6API.user.model.Role;
import com.dread9182.BTD6API.user.model.User;
import com.dread9182.BTD6API.user.model.request.UserAuthenticationRequest;
import com.dread9182.BTD6API.user.model.request.UserAuthenticationResponse;
import com.dread9182.BTD6API.user.model.request.UserRegisterRequest;
import com.dread9182.BTD6API.user.model.request.UserRegisterResponse;
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
	public UserRegisterResponse register(UserRegisterRequest request) {
		User verifyUniqueness = ur.findByEmail(request.getEmail()).orElse(null);
		if(verifyUniqueness != null)
			throw new ValueNotValidException("This email already has already been registered");
		
		User user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail().toLowerCase())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.ROLE_USER)
				.build();
		
		ur.save(user);
		
		String jwt = jwtService.generateToken(user);
		
		return UserRegisterResponse.builder()
				.message("The user was registered correctly")
				.build();
	}
	
	@Override
	public UserAuthenticationResponse authenticate(UserAuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail().toLowerCase(),
						request.getPassword()
				)
		);
		
		User user = ur.findByEmail(request.getEmail().toLowerCase()).orElseThrow(() ->
				new ValueNotValidException("Invalid user login data"));
		
		
		String jwt = jwtService.generateToken(user);
		
		return UserAuthenticationResponse.builder()
				.token(jwt)
				.build();
	}
}
