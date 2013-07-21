package com.kenjih.connectfour.main.games;

public class AgainstRuleException extends RuntimeException {

	private static final long serialVersionUID = 3904808602829900186L;

	public AgainstRuleException() {
		super();
	}

	public AgainstRuleException(String s) {
		super(s);
	}

	public AgainstRuleException(String message, Throwable cause) {
		super(message, cause);
	}

	public AgainstRuleException(Throwable cause) {
		super(cause);
	}
}
