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
}
