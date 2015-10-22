package team9.tutoragency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;
@Component
	public class SignupFormValidator implements Validator{

	@Autowired
	MemberDao memberDao;
	
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void validate(Object arg0, Errors errors) {
		SignupForm form = (SignupForm) arg0;
		if(!form.getPassword().equals(form.getPasswordConfirm())){
			errors.rejectValue("passwordConfirm", "password.mismatch","Passwörter stimmen nicht überein");
		}

		List<Member> registeredMembers  = memberDao.findByUsername(form.getUsername());
		if(registeredMembers.size() != 0){
			errors.rejectValue("username", "username.alreadyRegistered", "Username is already in use");
		}
		
		registeredMembers  = memberDao.findByEmail(form.getEmail());
		if(registeredMembers.size() != 0){
			errors.rejectValue("email", "email.alreadyRegistered", "Email is already in use");
		}
	}
	
}
