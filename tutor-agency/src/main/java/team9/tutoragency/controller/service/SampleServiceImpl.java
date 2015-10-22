package team9.tutoragency.controller.service;

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
public class SampleServiceImpl implements SampleService {

	@Autowired
	MemberDao memberDao;
	@Autowired
	AddressDao addDao;

	@Transactional
	public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException {

		
		/*
		 * if(!StringUtils.isEmpty(firstName) &&
		 * "ESE".equalsIgnoreCase(firstName)) { throw new InvalidUserException(
		 * "Sorry, ESE is not a valid name"); // throw exception }
		 */
		
		String firstName = signupForm.getFirstName();
		String lastName = signupForm.getLastName();
		String nickname = signupForm.getNickname();
		String email = signupForm.getEmail();
		String password = signupForm.getPassword();

		Address address = new Address();
		address.setStreet("TestStreet-foo");
		Member member = new Member(firstName,lastName,email,nickname,password);

		memberDao.save(member); // save object to DB


		signupForm.setId(member.getId());

		return signupForm;

	}
}
