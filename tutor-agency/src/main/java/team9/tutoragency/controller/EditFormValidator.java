package team9.tutoragency.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

@Component
public class EditFormValidator {
	@Autowired
	MemberDao memberDao;

	private Pattern validCharacterPattern;

	private Matcher matcher;
	private final String namePattern = "^[a-zA-Z0-9_-]{3,15}$";

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public EditFormValidator() {
		validCharacterPattern = Pattern.compile(namePattern);
	}

	public void validate(Object arg0, Errors errors) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();

		EditForm form = (EditForm) arg0;

		// If there is already a member with given username/email reject the
		// value
		List<Member> members = memberDao.findByUsername(form.getUsername());
		if (members.size() != 0 && members.get(0).equals(member)) {
			errors.rejectValue("username", "username.invalidName", "Dieser Username wird bereits verwendet");
		}
		members = memberDao.findByEmail(form.getEmail());
		if (members.size() != 0 && members.get(0).equals(member)) {
			errors.rejectValue("email", "email.invalidName", "Diese Email Adresse wird bereits verwendet");
		}

		matcher = validCharacterPattern.matcher(form.getUsername());

		if (!matcher.matches()) {
			errors.rejectValue("username", "username.invalidName", "Der Name sollte 3-15 Zeichen enthalten.");
		}

		if (!DigestUtils.md5Hex(form.getOldPassword()).equals(member.getPassword())) {
			errors.rejectValue("oldPassword", "oldPassword.invalidValue", "Altes Passwort ist nicht korrekt");
		}
		if (form.getPassword().length() != 0 && !form.getPassword().equals(form.getPasswordConfirm())) {
			errors.rejectValue("passwordConfirm", "password.mismatch", "Passwörter stimmen nicht überein");
		}
		try {
			Double.parseDouble(form.getFee());
		} catch (Exception e) {
			errors.rejectValue("fee", "fee.invalidValue","Bitte eine gültige Zahl eingeben");
		}

	}

}
