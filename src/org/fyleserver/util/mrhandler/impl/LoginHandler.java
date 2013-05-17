package org.fyleserver.util.mrhandler.impl;

import java.util.Calendar;

import org.fyleserver.data.lr.LRMessage;
import org.fyleserver.data.lr.LRResponse;
import org.fyleserver.db.UserDao;
import org.fyleserver.db.entity.User;
import org.fyleserver.db.impl.UserDaoImpl;
import org.fyleserver.localization.Localization;
import org.fyleserver.localization.impl.EnglishLocalizationImpl;
import org.fyleserver.util.hash.AesCbc;
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
	private UserDao userDAO;
	private Calendar calendar;
	private User user;
	
	public LoginHandler(LRMessage message) {
		this.userDAO = new UserDaoImpl();
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
		if(checkDB()){
			return errorMessage;
		}
		user.setLockout(0);
		user.setFailedLoginNum(0);
		user.setLastLoginTime(0l);
		userDAO.save(user);
		return new LRResponse("login", "ok").generateResponse();
	}
	
	private boolean checkDB(){
		user = userDAO.findByUsername(message.getUsername().toLowerCase());
		if(user!=null){
			calendar = Calendar.getInstance();
			user.setLoginTime(Math.round(calendar.getTimeInMillis()/1000d));
			System.out.println(user.getLoginTime());
			if((user.getLoginTime()-user.getLastLoginTime())<user.getLockout()){
				System.out.println(user.getLastLoginTime());
				System.out.println(user.getLockout());
				lrr = new LRResponse("login", local.getLoginWait(user.getLockout()-(user.getLoginTime()-user.getLastLoginTime())), "username");
				errorMessage = lrr.generateResponse();
				user.setLockout(user.getLockout()-(user.getLoginTime()-user.getLastLoginTime()));
				user.setLastLoginTime(user.getLoginTime());
				userDAO.save(user);
				return true;
			}
			if(!AesCbc.pwcheck(user.getPassword(), user.getPasswordhex(), message.getPassword())){
				user.setFailedLoginNum(user.getFailedLoginNum()+1);
				user.setLastLoginTime(user.getLoginTime());
				if(user.getFailedLoginNum()>4) user.setLockout(user.getFailedLoginNum()-4);
				userDAO.save(user);
				lrr = new LRResponse("login", local.getInvalidUsernameOrPassword(), "username");
				errorMessage = lrr.generateResponse();
				return true;
			}
		} else {
			lrr = new LRResponse("login", local.getInvalidUsernameOrPassword(), "username");
			errorMessage = lrr.generateResponse();
			return true;
		}
		return false;
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
