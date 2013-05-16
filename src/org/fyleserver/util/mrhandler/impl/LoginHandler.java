package org.fyleserver.util.mrhandler.impl;

import org.fyleserver.data.lr.LRMessage;
import org.fyleserver.util.mrhandler.MessageReceivedHandler;

public class LoginHandler implements MessageReceivedHandler {
	
	private LRMessage message;
	
	public LoginHandler(LRMessage message){
		this.message = message;
	}
	
	@Override
	public String createResponse(){
		return null;
	}
}
