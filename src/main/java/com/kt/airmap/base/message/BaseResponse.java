package com.kt.airmap.base.message;

import java.io.Serializable;

import com.kt.airmap.api.Page;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = -1020211389560326967L;
	
	private String responseCode;
	private String message;
	private Page paging;
	private Object data;

	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Page getPaging() {
		return paging;
	}
	public void setPaging(Page paging) {
		this.paging = paging;
	}
}
