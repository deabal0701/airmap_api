package com.kt.airmap.base.exception;

import com.kt.airmap.base.exception.BaseException;

public class DataNotFoundException extends BaseException {

	private static final long serialVersionUID = 81963875134266622L;

	public DataNotFoundException() {
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
