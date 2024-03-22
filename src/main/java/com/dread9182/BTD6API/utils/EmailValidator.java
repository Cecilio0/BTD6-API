package com.dread9182.BTD6API.utils;

import com.dread9182.BTD6API.exception.ValueNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	
	private static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
	
	public static boolean validate(String email){
		Matcher matcher = VALID_EMAIL_REGEX.matcher(email);
		return matcher.find();
	}
}
