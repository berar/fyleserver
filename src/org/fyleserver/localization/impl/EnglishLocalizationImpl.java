package org.fyleserver.localization.impl;

import org.fyleserver.localization.Localization;

public class EnglishLocalizationImpl implements Localization {
	
	@Override
	public String getRegistrationFatalError(){
		return "Fatal error while registering.";
	}
	
	@Override
    public String getThisFieldIsEmpty() {
        return "This field is empty.";
    }

    @Override
    public String getUsernameTooShort() {
        return "Username min. length is 4.";
    }

    @Override
    public String getUsernameTooLarge() {
        return "Username max. length is 16";
    }

    @Override
    public String getUsernameInvalidFormat() {
        return "Username can only a-z, A-Z, 0-9, dashes or dot.";
    }

    @Override
    public String getPasswordTooShort() {
        return "Password min. length is 8.";
    }

    @Override
    public String getPasswordTooLarge() {
        return "Password max. length is 128.";
    }

    @Override
    public String getPasswordInvalidFormat() {
        return "At least one A-Z, a-z, 0-9 and no spaces.";
    }

    @Override
    public String getEmailTooShort() {
        return "Email is too short.";
    }

    @Override
    public String getEmailTooLarge() {
        return "Email is too large.";
    }

    @Override
    public String getEmailInvalidFormat() {
        return "Invalid email format.";
    }

    @Override
    public String getPasswordsDontMatch() {
        return "Passwords don't match.";
    }
}
