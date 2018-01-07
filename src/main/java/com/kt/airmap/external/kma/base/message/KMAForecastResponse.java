package com.kt.airmap.external.kma.base.message;

import java.io.Serializable;


public class KMAForecastResponse implements Serializable {

	private static final long serialVersionUID = -1020211389560326967L;

	private Object response;
	
	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
}
