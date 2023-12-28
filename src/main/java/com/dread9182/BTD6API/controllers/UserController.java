package com.dread9182.BTD6API.controllers;

import com.dread9182.BTD6API.auxiliaryclass.UserAuthenticationRequest;
import com.dread9182.BTD6API.auxiliaryclass.UserAuthenticationResponse;
import com.dread9182.BTD6API.auxiliaryclass.UserRegisterRequest;
import com.dread9182.BTD6API.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private IUserService us;
	
	@PostMapping("/register")
	public ResponseEntity<UserAuthenticationResponse> register(
			@RequestBody UserRegisterRequest request
	) {
		return new ResponseEntity<>(us.register(request), HttpStatus.OK);
	}
	@PostMapping("/authenticate")
	public ResponseEntity<UserAuthenticationResponse> authenticate(
			@RequestBody UserAuthenticationRequest request
	) {
		//todo implement Exceptions
		return new ResponseEntity<>(us.authenticate(request), HttpStatus.OK);
	}
	
	
}
