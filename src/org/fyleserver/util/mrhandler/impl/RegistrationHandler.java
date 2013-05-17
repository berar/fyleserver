package org.fyleserver.util.mrhandler.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.fyleserver.data.lr.LRMessage;
import org.fyleserver.data.lr.LRResponse;
import org.fyleserver.db.UserDao;
import org.fyleserver.db.entity.User;
import org.fyleserver.db.impl.UserDaoImpl;
import org.fyleserver.localization.Localization;
import org.fyleserver.localization.impl.EnglishLocalizationImpl;
import org.fyleserver.util.Constants;
import org.fyleserver.util.hash.AesCbc;
import org.fyleserver.util.hash.RandomHex32;
import org.fyleserver.util.mrhandler.MessageReceivedHandler;
import org.fyleserver.util.mrhandler.validation.LRValidator;
import org.fyleserver.util.mrhandler.validation.impl.LRValidatorImpl;
import org.fyleserver.util.mrhandler.validation.impl.ValidationResult;

public class RegistrationHandler implements MessageReceivedHandler {
	
	private static final Date dt = new Date();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Localization local = new EnglishLocalizationImpl();
	private String errorMessage;
	private boolean isFatal;
	private LRResponse lrr;
	private LRMessage message;
	private UserDao userDAO;
	
	public RegistrationHandler(LRMessage message) {
		this.message = message;
		this.userDAO = new UserDaoImpl();
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
		if(checkDB()){
			return errorMessage;
		}
		String currentTime = sdf.format(dt);
		String passhex = RandomHex32.randomHex(Constants.secureRND);
		String password = AesCbc.encrypt(message.getPassword(), passhex, Constants.secureRND);
		User user = new User();
		user.setUsername(message.getUsername());
		user.setEmail(message.getEmail());
		user.setRegistrationDate(currentTime);
		user.setPassword(password);
		user.setPasswordhex(passhex);
		userDAO.save(user);
		return new LRResponse("register", local.getRegistrationComplete() + message.getUsername() + "!").generateResponse();
	}
	
	private boolean checkDB() {
		User user = userDAO.findByUsername(message.getUsername());
		if(user!=null){
			lrr = new LRResponse("register", local.getUsernameIsTaken(), "username");
			errorMessage = lrr.generateResponse();
			return true;
		}
		user = userDAO.findByEmail(message.getEmail());
		if(user!=null){
			lrr = new LRResponse("register", local.getEmailAlreadyExists(), "email");
			errorMessage = lrr.generateResponse();
			return true;
		}
		return false;
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
