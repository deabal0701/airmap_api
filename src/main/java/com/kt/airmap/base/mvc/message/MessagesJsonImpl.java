
package com.kt.airmap.base.mvc.message;

import java.util.ArrayList;
import java.util.List;

import com.kt.airmap.api.Page;

public class MessagesJsonImpl implements Messages, MessageAccessor{

	private List<Message> messages = new ArrayList<Message>();

	@Override
	public void addMessage(String code, String msg) {
		addMessage(code, msg, null);
	}

	@Override
	public void addMessage(String code, String msg, Page paging) {
		messages.add(new Message(code, msg, paging));
	}

	@Override
	public List<Message> getMessageList() {
		return messages;
	}

}
