package com.tech.exception;

/**
 * 账号过期异常。
 */
@SuppressWarnings("serial")
public class AccountExpiredException extends AccountStatusException {
	public AccountExpiredException() {
	}

	public AccountExpiredException(String msg) {
		super(msg);
	}

	public AccountExpiredException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}
