package team9.tutoragency.controller.service.validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import team9.tutoragency.controller.pojos.Form;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

/**
 * The {@link SignupFormValidator} validates a {@link SignupForm}.
 * 
 * @author laeri
 *
 */
@Service
public class SignupFormValidator implements Validator {

	@Autowired
	MemberDao memberDao;

	private Pattern validCharacterPattern;

	private Matcher matcher;
	private final String namePattern = "^[a-zA-Z0-9_-]{3,15}$";

	public SignupFormValidator() {
		validCharacterPattern = Pattern.compile(namePattern);
	}

	private void checkUsernameAlreadyInUse(Errors errors, SignupForm form) {
		List<Member> registeredMembers = memberDao.findByUsername(form.getUsername());
		if (registeredMembers.size() != 0) {
			errors.rejectValue("username", "username.alreadyRegistered", "Username already in use");
		}
	}

	private void checkEmailAlreadyInUse(Errors errors, SignupForm form) {
		List<Member> registeredMembers;
		registeredMembers = memberDao.findByEmail(form.getEmail());
		if (registeredMembers.size() != 0) {
			errors.rejectValue("email", "email.alreadyRegistered", "Email already in use");
		}
	}

	
	@Override
	public void validate(Object target, Errors errors) {
		SignupForm form = (SignupForm) target;
		matcher = validCharacterPattern.matcher(form.getUsername());
		if (!matcher.matches()) {
			errors.rejectValue("username", "username.invalidName", "Username needs to be 3-15 characters long");
		}

		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			errors.rejectValue("passwordConfirm", "password.mismatch", "Passwords do not match");
		}

		if (!form.isReadAGB()) {
			errors.rejectValue("readAGB", "readAGB.notRead", "Please read and confirm the Terms and Conditions");
		}
		checkUsernameAlreadyInUse(errors, form);

		checkEmailAlreadyInUse(errors, form);

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return SignupForm.class.isAssignableFrom(clazz);
	}
}
