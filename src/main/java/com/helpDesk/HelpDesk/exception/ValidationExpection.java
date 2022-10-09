package com.helpDesk.HelpDesk.exception;

public class ValidationExpection extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9108484423372362903L;

	public ValidationExpection(String msg) {
		super(msg);
	}
	
	public ValidationExpection() {}

}
