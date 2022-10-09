package com.helpDesk.HelpDesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnauthorisedExpection extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorisedExpection(String msg) {
		super(msg);
	}

}
