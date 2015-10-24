package team9.tutoragency.controller.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import team9.tutoragency.controller.exceptions.InvalidUserException;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.model.Address;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.AddressDao;
import team9.tutoragency.model.dao.MemberDao;

@Service
public class RegistrationService implements SampleService {

	@Autowired
	MemberDao memberDao;

	
	@Transactional
	public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException {

		String firstName = signupForm.getFirstName();
		String lastName = signupForm.getLastName();
		String nickname = signupForm.getUsername();
		String email = signupForm.getEmail();
		String password = DigestUtils.md5Hex(signupForm.getPassword());

		Member member = new Member(firstName,lastName,email,nickname,password);

		memberDao.save(member);

		return signupForm;

	}
}
