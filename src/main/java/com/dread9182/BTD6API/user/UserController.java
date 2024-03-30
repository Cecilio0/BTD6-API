package com.dread9182.BTD6API.user;

import com.dread9182.BTD6API.user.model.request.UserAuthenticationRequest;
import com.dread9182.BTD6API.user.model.request.UserAuthenticationResponse;
import com.dread9182.BTD6API.user.model.request.UserRegisterRequest;
import com.dread9182.BTD6API.user.model.request.UserRegisterResponse;
import com.dread9182.BTD6API.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(
		name = "User controller",
		description = "Controller in charge of all requests related to user registration and authentication"
)
public class UserController {
	
	@Autowired
	private final IUserService us;
	
	@Operation(
			summary = "Register a new user"
	)
	@PostMapping("/register")
	public ResponseEntity<UserRegisterResponse> register(
			@RequestBody UserRegisterRequest request
	) {
		return new ResponseEntity<>(us.register(request), HttpStatus.CREATED);
	}
	
	@Operation(
			summary = "Authenticate an existing user"
	)
	@PostMapping("/authenticate")
	public ResponseEntity<UserAuthenticationResponse> authenticate(
			@RequestBody UserAuthenticationRequest request
	) {
		return new ResponseEntity<>(us.authenticate(request), HttpStatus.OK);
	}
	
	
}
