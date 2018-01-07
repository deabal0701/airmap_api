package com.kt.airmap.base.mvc.message;

import com.kt.airmap.api.Page;

public class Message {
	private String code;
	private String msg;
	private Page paging;

	public Message(String code, String msg){
		this(code, msg, null);
	}

	public Message(String code, String msg, Page paging) {
		this.code = code;
		this.msg = msg;
		this.paging = paging;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Page getPaging() {
		return paging;
	}

	public void setPaging(Page paging) {
		this.paging = paging;
	}

}
