package com.kenjih.connectfour.main.games;

public class TimeOverException extends RuntimeException {

	private static final long serialVersionUID = 3904808602829900186L;

	public TimeOverException() {
		super();
	}

	public TimeOverException(String s) {
		super(s);
	}

	public TimeOverException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeOverException(Throwable cause) {
		super(cause);
	}
}
