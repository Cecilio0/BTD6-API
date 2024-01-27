package com.dread9182.BTD6API.user;

import com.dread9182.BTD6API.user.model.request.UserAuthenticationRequest;
import com.dread9182.BTD6API.user.model.request.UserAuthenticationResponse;
import com.dread9182.BTD6API.user.model.request.UserRegisterRequest;
import com.dread9182.BTD6API.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private final IUserService us;
	
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
