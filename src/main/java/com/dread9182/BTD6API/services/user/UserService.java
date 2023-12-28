package com.dread9182.BTD6API.services.user;

import com.dread9182.BTD6API.auxiliaryclass.UserAuthenticationRequest;
import com.dread9182.BTD6API.auxiliaryclass.UserAuthenticationResponse;
import com.dread9182.BTD6API.auxiliaryclass.UserRegisterRequest;
import com.dread9182.BTD6API.config.JwtService;
import com.dread9182.BTD6API.models.Role;
import com.dread9182.BTD6API.models.User;
import com.dread9182.BTD6API.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository ur;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public UserAuthenticationResponse register(UserRegisterRequest request) {
		User user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		
		ur.save(user);
		
		String jwt = jwtService.generateToken(user);
		
		return UserAuthenticationResponse.builder()
				.token(jwt)
				.build();
	}
	
	@Override
	public UserAuthenticationResponse authenticate(UserAuthenticationRequest request) {
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
