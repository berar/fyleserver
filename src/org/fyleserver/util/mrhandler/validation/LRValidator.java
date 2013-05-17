package org.fyleserver.util.mrhandler.validation;

import org.fyleserver.util.mrhandler.validation.impl.ValidationResult;

/**
*
* @author berar
*/
public interface LRValidator {
   
   ValidationResult isUsernameCorrect(String username);
   ValidationResult isPasswordCorrect(String password, String reppassword);
   ValidationResult isEmailCorrect(String email);
   
}
