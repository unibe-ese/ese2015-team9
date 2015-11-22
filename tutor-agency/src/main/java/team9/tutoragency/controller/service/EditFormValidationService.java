package team9.tutoragency.controller.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javassist.bytecode.AnnotationsAttribute;
import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.controller.pojos.Form;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

/**
 * The EditFormValidationService validates ({@link #validate(Form, Errors)}) the
 * {@link EditForm} which is used to edit changes to the profile of a
 * {@link Member}.
 * 
 * An {@link EditForm} is valid if following conditions are met:
 * <ol>
 * <li>Neither the email, nor the username is already in use:
 * {@link #checkEmailAlreadyInUse(Errors, Member, EditForm)},
 * {@link #checkUsernameAlreadyInUse(Errors, Member, EditForm)}</li>
 * <li>The {@link EditForm#getOldPassword()} which was typed in by the user
 * should match the password which is already in the database
 * {@link Member#getPassword()}. The password is stored as an MD5 hash,
 * therefore the old password from the form is first hashed before the equality
 * check.</li>
 * <li>If the {@link EditForm#getPassword()} is not empty, the user would like
 * to change the password. If the {@link EditForm#getPassword()} doesn't match
 * the {@link EditForm#getPasswordConfirm()}, the form is not valid.</li>
 * <li>If the member is tutor, the fee of the tutoring session has to be filled
 * out {@link EditForm#getFee()}</li>
 * </ol>
 * In every case the old password has to be supplied for security reasons.
 *  If a
 * form is not valid, an error message will be displayed in the view in order to
 * allow the member to change the input values. Simple null checks are
 * implemented with {@link AnnotationsAttribute} in the {@link EditForm}.
 * 
 * @author laeri
 *
 */
@Service
public class EditFormValidationService implements ValidationService {
	@Autowired
	MemberDao memberDao;
	@Autowired
	MemberService memberService;

	private Pattern validCharacterPattern;

	private Matcher matcher;
	private final String namePattern = "^[a-zA-Z0-9_-]{3,15}$";

	public EditFormValidationService() {
		validCharacterPattern = Pattern.compile(namePattern);
	}

	private void checkEmailAlreadyInUse(Errors errors, Member member, EditForm form) {
		List<Member> members = memberDao.findByEmail(form.getEmail());
		if (!members.get(0).equals(member)) {
			errors.rejectValue("email", "email.invalidName", "Diese Email Adresse wird bereits verwendet");
		}
	}

	private void checkUsernameAlreadyInUse(Errors errors, Member member, EditForm form) {
		List<Member> members = memberDao.findByUsername(form.getUsername());
		if (!members.get(0).equals(member)) {
			errors.rejectValue("username", "username.invalidName", "Dieser Username wird bereits verwendet");
		}
	}

	public void validate(Form editForm, Errors errors) {
		Member member = memberService.getAuthenticatedMember().get();

		EditForm form = (EditForm) editForm;

		checkUsernameAlreadyInUse(errors, member, form);
		checkEmailAlreadyInUse(errors, member, form);

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

		if (member.isIsTutor()) {
			try {
				Double.parseDouble(form.getFee());
			} catch (Exception e) {
				errors.rejectValue("fee", "fee.invalidValue", "Bitte eine gültige Zahl eingeben");
			}
		}
	}

}
