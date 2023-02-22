package com.etra.reactiveNotes.reactiveNotes.controller.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WebException extends ResponseStatusException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 167365238848154856L;

	public WebException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}