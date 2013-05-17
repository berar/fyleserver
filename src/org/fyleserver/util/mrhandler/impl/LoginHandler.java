package org.fyleserver.util.mrhandler.impl;

import org.fyleserver.data.lr.LRMessage;
import org.fyleserver.data.lr.LRResponse;
import org.fyleserver.localization.Localization;
import org.fyleserver.localization.impl.EnglishLocalizationImpl;
import org.fyleserver.util.mrhandler.MessageReceivedHandler;
import org.fyleserver.util.mrhandler.validation.LRValidator;
import org.fyleserver.util.mrhandler.validation.impl.LRValidatorImpl;
import org.fyleserver.util.mrhandler.validation.impl.ValidationResult;

public class LoginHandler implements MessageReceivedHandler {

	private static Localization local = new EnglishLocalizationImpl();
	private String errorMessage;
	private boolean isFatal;
	private LRMessage message;
	private LRResponse lrr;

	public LoginHandler(LRMessage message) {
		this.message = message;
	}

	@Override
	public String createResponse() {
		// TODO
		if (isFatal) {
			return "login ERROR " + local.getRegistrationFatalError()
					+ " BUTTON";
		}
		if (hasError()) {
			return errorMessage;
		}
		// TODO
		return "LOGIN OK";
	}
	
	private boolean hasError(){
		String username = message.getUsername();
		String password = message.getPassword();
		if (username.length() == 0){
			lrr = new LRResponse("login", local.getThisFieldIsEmpty(), "username");
			errorMessage = lrr.generateResponse();
			return true;
		}
		if (password.length() == 0){
			lrr = new LRResponse("login", local.getThisFieldIsEmpty(), "password");
			errorMessage = lrr.generateResponse();
			return true;
		}
		LRValidator lrv = new LRValidatorImpl(local);
		ValidationResult vr;
		vr = lrv.isUsernameCorrect(username);
		if (!vr.isValid()) {
			lrr = new LRResponse("login", vr.getMessage(), "username");
			errorMessage = lrr.generateResponse();
			return true;
		}
		vr = lrv.isPasswordCorrect(password, password);
		if (!vr.isValid()){
			lrr = new LRResponse("login", vr.getMessage(), "password");
			errorMessage = lrr.generateResponse();
			return true;
		}
		return false;
	}
}
