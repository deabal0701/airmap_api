
package com.kt.airmap.base.message;

public enum ResponseCode {

	OK ("200", "OK"),
	INTERNAL_AUTHENTICATION_FAILED ("400", "Authentication Failed"),
	EXTERNAL_TIMEOUT ("450", "Timeout");

	private final String value;
	private final String reasonPhrase;

	private ResponseCode(String value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public String getValue() {
		return this.value;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}
	
	public static ResponseCode parseType(String value) {
		for (ResponseCode responseCode : values()) {
			if (responseCode.getValue().equals(value)) {
				return responseCode;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + value + "]");
	}
}
