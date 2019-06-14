package com.nagarro.pos.utils;

import org.springframework.http.HttpStatus;

import com.nagarro.pos.models.ErrorMessage;

/**
 * this is error payload class
 * 
 * @author damanpreetbrar
 *
 */
public class ErrorPayload {

	public static ErrorMessage createErrorPayload(HttpStatus status, String message) {
		return new ErrorMessage(status.value(), message);
	}
}
