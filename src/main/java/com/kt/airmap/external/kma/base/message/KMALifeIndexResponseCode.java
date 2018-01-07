package com.kt.airmap.external.kma.base.message;

public enum KMALifeIndexResponseCode {

	OK ("00", "Y");

	private final String value;
	private final String reasonPhrase;

	private KMALifeIndexResponseCode(String value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public String getValue() {
		return this.value;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}
	
	public static KMALifeIndexResponseCode parseType(String value) {
		for (KMALifeIndexResponseCode kmaLifeIndexResponseCode : values()) {
			if (kmaLifeIndexResponseCode.getValue().equals(value)) {
				return kmaLifeIndexResponseCode;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + value + "]");
	}
}
