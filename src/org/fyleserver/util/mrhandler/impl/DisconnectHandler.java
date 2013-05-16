package org.fyleserver.util.mrhandler.impl;

import org.fyleserver.util.mrhandler.MessageReceivedHandler;


public class DisconnectHandler implements MessageReceivedHandler {

	@Override
	public String createResponse() {
		return "DISC";
	}

}
