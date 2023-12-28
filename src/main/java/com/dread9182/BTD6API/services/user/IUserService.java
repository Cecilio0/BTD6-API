package com.dread9182.BTD6API.services.user;

import com.dread9182.BTD6API.auxiliaryclass.UserAuthenticationRequest;
import com.dread9182.BTD6API.auxiliaryclass.UserAuthenticationResponse;
import com.dread9182.BTD6API.auxiliaryclass.UserRegisterRequest;

public interface IUserService {
	UserAuthenticationResponse register(UserRegisterRequest request);
	
	UserAuthenticationResponse authenticate(UserAuthenticationRequest request);
}
