package org.fyleserver.localization;

public interface Localization {
	//registration errors or OK
	String getRegistrationFatalError();
	
	String getThisFieldIsEmpty();
    String getUsernameTooShort();
    String getUsernameTooLarge();
    String getUsernameInvalidFormat();
    String getPasswordTooShort();
    String getPasswordTooLarge();
    String getPasswordInvalidFormat();
    String getPasswordsDontMatch();
    String getEmailTooShort();
    String getEmailTooLarge();
    String getEmailInvalidFormat();
    
    String getUsernameIsTaken();
    String getEmailAlreadyExists();
    String getInvalidUsernameOrPassword();
    String getLoginWait(long sec);
    String getRegistrationComplete();
}
