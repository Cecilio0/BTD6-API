package com.dread9182.BTD6API.user.service;

import com.dread9182.BTD6API.user.model.request.UserAuthenticationRequest;
import com.dread9182.BTD6API.user.model.request.UserAuthenticationResponse;
import com.dread9182.BTD6API.user.model.request.UserRegisterRequest;

public interface IUserService {
	UserAuthenticationResponse register(UserRegisterRequest request);
	
	UserAuthenticationResponse authenticate(UserAuthenticationRequest request);
}
