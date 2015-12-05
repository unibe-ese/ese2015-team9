package util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.service.validation.SignupFormValidator;

public class SignupFormValidatorMock extends SignupFormValidator{

	private boolean rejecting;
	
	public SignupFormValidatorMock(boolean rejecting){
		this.rejecting = rejecting;
	}
	@Override
	public void validate(Object o, Errors errors){
		if(rejecting)
			errors.reject("error");
	}
	
	
}
