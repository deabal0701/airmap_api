
package com.kt.airmap.external.kma.base.message;

public enum KMAForecastResponseCode {

	OK ("0000", "OK"),
	INTERNAL_AUTHENTICATION_FAILED ("400", "Authentication Failed");

	private final String value;
	private final String reasonPhrase;

	private KMAForecastResponseCode(String value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public String getValue() {
		return this.value;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}
	
	public static KMAForecastResponseCode parseType(String value) {
		for (KMAForecastResponseCode kMAForecastResponseCode : values()) {
			if (kMAForecastResponseCode.getValue().equals(value)) {
				return kMAForecastResponseCode;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + value + "]");
	}

}