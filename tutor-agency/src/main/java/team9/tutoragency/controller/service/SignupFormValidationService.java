package team9.tutoragency.controller.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import team9.tutoragency.controller.pojos.Form;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

@Service
public class SignupFormValidationService implements ValidationService {

	@Autowired
	MemberDao memberDao;

	private Pattern validCharacterPattern;

	private Matcher matcher;
	private final String namePattern = "^[a-zA-Z0-9_-]{3,15}$";


	public SignupFormValidationService() {
		validCharacterPattern = Pattern.compile(namePattern);
	}


	private void checkUsernameAlreadyInUse(Errors errors, SignupForm form) {
		List<Member> registeredMembers = memberDao.findByUsername(form.getUsername());
		if (registeredMembers.size() != 0) {
			errors.rejectValue("username", "username.alreadyRegistered", "Nickname wird bereits verwendet");
		}
	}

	private void checkEmailAlreadyInUse(Errors errors, SignupForm form) {
		List<Member> registeredMembers;
		registeredMembers = memberDao.findByEmail(form.getEmail());
		if (registeredMembers.size() != 0) {
			errors.rejectValue("email", "email.alreadyRegistered", "Email wird bereits verwendet");
		}
	}

	public void validate(Form signupForm, Errors errors) {
		SignupForm form = (SignupForm) signupForm;
		matcher = validCharacterPattern.matcher(form.getUsername());
		if (!matcher.matches()) {
			errors.rejectValue("username", "username.invalidName", "Der Name sollte 3-15 Zeichen enthalten.");
		}

		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			errors.rejectValue("passwordConfirm", "password.mismatch", "Passwörter stimmen nicht überein");
		}

		if (!form.isReadAGB()) {
			errors.rejectValue("readAGB", "readAGB.notRead", "Bitte lies die AGB und bestätige dies mit einem Klick.");
		}
		checkUsernameAlreadyInUse(errors, form);

		checkEmailAlreadyInUse(errors, form);
		
	}

}