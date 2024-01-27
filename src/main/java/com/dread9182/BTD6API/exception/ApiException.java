package com.dread9182.BTD6API.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public record ApiException(@Getter String message, @Getter HttpStatus httpStatus) { }
