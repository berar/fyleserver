package org.fyleserver.util.mrhandler.impl;

import org.fyleserver.data.lr.LRMessage;
import org.fyleserver.data.lr.LRResponse;
import org.fyleserver.localization.Localization;
import org.fyleserver.localization.impl.EnglishLocalizationImpl;
import org.fyleserver.util.mrhandler.MessageReceivedHandler;
import org.fyleserver.util.mrhandler.validation.LRValidator;
import org.fyleserver.util.mrhandler.validation.impl.LRValidatorImpl;
import org.fyleserver.util.mrhandler.validation.impl.ValidationResult;

public class RegistrationHandler implements MessageReceivedHandler {

	private static Localization local = new EnglishLocalizationImpl();
	private String errorMessage;
	private boolean isFatal;
	private LRResponse lrr;
	private LRMessage message;
	
	public RegistrationHandler(LRMessage message) {
		this.message = message;
		this.isFatal = false;
	}

	@Override
	public String createResponse() {
		//TODO
		if (isFatal) {
			return "REGISTER ERROR "+local.getRegistrationFatalError()+" BUTTON";
		}
		if (hasError()) {
			return errorMessage;
		}
		//TODO
		return "REGISTER OK";
	}

	private boolean hasError() {
		String username = message.getUsername();
		String email = message.getEmail();
		String password = message.getPassword();
		String reppassword = message.getReppassword();
		if (username.length() == 0){
			lrr = new LRResponse("register", local.getThisFieldIsEmpty(), "username");
			errorMessage = lrr.generateResponse();
			return true;
		}
		if (email.length() == 0){
			lrr = new LRResponse("register", local.getThisFieldIsEmpty(), "email");
			errorMessage = lrr.generateResponse();
			return true;
		}
		if (password.length() == 0){
			lrr = new LRResponse("register", local.getThisFieldIsEmpty(), "password");
			errorMessage = lrr.generateResponse();
			return true;
		}
		if (reppassword.length() == 0){
			lrr = new LRResponse("register", local.getThisFieldIsEmpty(), "reppass");
			errorMessage = lrr.generateResponse();
			return true;
		}
		LRValidator lrv = new LRValidatorImpl(local);
		ValidationResult vr;
		vr = lrv.isUsernameCorrect(username);
		if (!vr.isValid()) {
			lrr = new LRResponse("register", vr.getMessage(), "username");
			errorMessage = lrr.generateResponse();
			return true;
		}
		vr = lrv.isEmailCorrect(email);
		if (!vr.isValid()){
			lrr = new LRResponse("register", vr.getMessage(), "email");
			errorMessage = lrr.generateResponse();
			return true;
		}
		vr = lrv.isPasswordCorrect(password, reppassword);
		if (!vr.isValid()){
			lrr = new LRResponse("register", vr.getMessage(), "password");
			errorMessage = lrr.generateResponse();
			return true;
		}
		return false;
	}
}
