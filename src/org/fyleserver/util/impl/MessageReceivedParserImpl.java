package org.fyleserver.util.impl;

import java.util.regex.Pattern;

import org.fyleserver.data.lr.LRMessage;
import org.fyleserver.util.MessageReceivedParser;
import org.fyleserver.util.mrhandler.MessageReceivedHandler;
import org.fyleserver.util.mrhandler.impl.DisconnectHandler;
import org.fyleserver.util.mrhandler.impl.LoginHandler;
import org.fyleserver.util.mrhandler.impl.RegistrationHandler;

public class MessageReceivedParserImpl implements MessageReceivedParser {
	private static final Pattern RGX_PATTERN_LRMESSAGE = Pattern.compile("^(?i)<lrmessage([^>]+)>(.+?)</lrmessage>");
	private static final Pattern RGX_PATTERN_DISCMESSAGE = Pattern.compile("^(?i)<discmessage>(.+?)</discmessage>");
	
	private String receivedMessage;
	private String type;

	public MessageReceivedParserImpl(String message) {
		this.receivedMessage = message;
		init();
	}

	private void init() {
		if(RGX_PATTERN_LRMESSAGE.matcher(receivedMessage).matches()){
			this.type = "LR";
			return;
		}
		if(RGX_PATTERN_DISCMESSAGE.matcher(receivedMessage).matches()){
			this.type = "DISC";
			return;
		}
	}

	@Override
	public MessageReceivedHandler handle() {
		if ("LR".equals(this.type)) {
			LRMessage lrm = LRMessage.createLRMessage(receivedMessage);
			if ("register".equals(lrm.getType())) {
				return new RegistrationHandler(lrm);
			}
			if ("login".equals(lrm.getType())) {
				return new LoginHandler(lrm);
			}
		}
		if ("DISC".equals(this.type)){
			return new DisconnectHandler();
		}
		System.out.println("Error or not supported yet.");
		return null;
	}
	
}
