package com.kt.airmap.external.kma.base.message;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMALifeindexResponse implements Serializable{
  
	private static final long serialVersionUID = 1L;

	@JsonProperty("Response")
	private Object Response;

	public Object getResponse() {
		return Response;
	}

	public void setResponse(Object Response) {
		this.Response = Response;
	}
	
	
	
}
