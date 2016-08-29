package com.tech.exception;

/**
 * Rule violation exception.
 * 
 * @author pzp
 * @since 2015-09-08
 */
public class RuleViolationException extends RuntimeException{
	
	private static final long serialVersionUID = 5644344946335495856L;
	
	public RuleViolationException(){
		
	}
	
	public RuleViolationException(final String message){
		super(message);
	}
	
	public RuleViolationException(final Throwable cause){
		super(cause);
	}

	public RuleViolationException(final String message, final Throwable cause){
		super(message, cause);
	}
	
}
