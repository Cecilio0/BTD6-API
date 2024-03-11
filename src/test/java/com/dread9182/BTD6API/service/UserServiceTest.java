package com.dread9182.BTD6API.service;

import com.dread9182.BTD6API.config.JwtService;
import com.dread9182.BTD6API.exception.NotFoundException;
import com.dread9182.BTD6API.exception.ValueNotValidException;
import com.dread9182.BTD6API.user.IUserRepository;
import com.dread9182.BTD6API.user.model.Role;
import com.dread9182.BTD6API.user.model.User;
import com.dread9182.BTD6API.user.model.request.UserAuthenticationRequest;
import com.dread9182.BTD6API.user.model.request.UserAuthenticationResponse;
import com.dread9182.BTD6API.user.model.request.UserRegisterRequest;
import com.dread9182.BTD6API.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	// InjectMocks annotation indicates the class in which the mocks will be injected
	@InjectMocks
	@Autowired
	private UserService us;
	
	// Mock annotation indicates which classes will be mocked
	@Mock
	private IUserRepository ur;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private JwtService jwtService;
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	@Test
	public void UserService_RegisterUser_ReturnsUserAuthenticationResponse() {
		// We build an Item for the repository to return
		User user = User.builder()
				.id("7d5c97c0-74b1-4bef-b297-1ea357efd82a")
				.firstName("John")
				.lastName("Doe")
				.email("John.Doe@gmail.com")
				.password("Password123")
				.role(Role.ROLE_USER)
				.build();
		
		// We build the UserRegisterRequest that will be sent to the register method
		UserRegisterRequest registerRequest = UserRegisterRequest.builder()
				.firstName("John")
				.lastName("Doe")
				.email("John.Doe@gmail.com")
				.password("Password123")
				.build();
		
		// We tell the mocked classes which methods should be mocked and what they should return if called
		Mockito.when(ur.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(ur.save(Mockito.any(User.class))).thenReturn(user);
		Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("Password123");
		Mockito.when(jwtService.generateToken(Mockito.any(User.class))).thenReturn("token");
		
		// We return an object of the expected class and subsequently test if it is not null
		UserAuthenticationResponse userResponse = us.register(registerRequest);
		Assertions.assertNotNull(userResponse);
		Assertions.assertEquals("token", userResponse.getToken());
	}
	
	@Test
	public void UserService_RegisterUser_ThrowsValueNotValidException() {
		// We build an Item for the repository to return
		User user = User.builder()
				.id("7d5c97c0-74b1-4bef-b297-1ea357efd82a")
				.firstName("John")
				.lastName("Doe")
				.email("John.Doe@gmail.com")
				.password("Password123")
				.role(Role.ROLE_USER)
				.build();
		
		// We build the UserRegisterRequest that will be sent to the register method
		UserRegisterRequest registerRequest = UserRegisterRequest.builder()
				.firstName("John")
				.lastName("Doe")
				.email("John.Doe@gmail.com")
				.password("Password123")
				.build();
		
		// We tell the mocked classes which methods should be mocked and what they should return if called
		Mockito.when(ur.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
		
		// We check that when we run the method an exception is thrown, and it has type ValueNotValidException
		Assertions.assertThrows(ValueNotValidException.class, () -> us.register(registerRequest));
	}
	
	@Test
	public void UserService_AuthenticateUser_ReturnsUserAuthenticationResponse() {
		// We build an Item for the repository to return
		User user = User.builder()
				.id("7d5c97c0-74b1-4bef-b297-1ea357efd82a")
				.firstName("John")
				.lastName("Doe")
				.email("John.Doe@gmail.com")
				.password("Password123")
				.role(Role.ROLE_USER)
				.build();
		
		UserAuthenticationRequest userAuthenticationRequest = UserAuthenticationRequest.builder()
				.email("John.Doe@gmail.com")
				.password("Password123")
				.build();
		
		// We tell the mocked classes which methods should be mocked and what they should return if called
		Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(null);
		Mockito.when(ur.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
		Mockito.when(jwtService.generateToken(Mockito.any(User.class))).thenReturn("token");
		
		// We return an object of the expected class and subsequently test if it is not null
		UserAuthenticationResponse userResponse = us.authenticate(userAuthenticationRequest);
		Assertions.assertNotNull(userResponse);
		Assertions.assertEquals("token", userResponse.getToken());
	}
	
	@Test
	public void UserService_AuthenticateUser_ThrowsNotFoundException() {
		// We build an Item for the repository to return
		User user = User.builder()
				.id("7d5c97c0-74b1-4bef-b297-1ea357efd82a")
				.firstName("John")
				.lastName("Doe")
				.email("John.Doe@gmail.com")
				.password("Password123")
				.role(Role.ROLE_USER)
				.build();
		
		UserAuthenticationRequest userAuthenticationRequest = UserAuthenticationRequest.builder()
				.email("John.Doe@gmail.com")
				.password("Password123")
				.build();
		
		// We tell the mocked classes which methods should be mocked and what they should return if called
		Mockito.when(ur.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		
		// We check that when we run the method an exception is thrown, and it has type NotFoundException
		Assertions.assertThrows(NotFoundException.class, () -> us.authenticate(userAuthenticationRequest));
	}

}
