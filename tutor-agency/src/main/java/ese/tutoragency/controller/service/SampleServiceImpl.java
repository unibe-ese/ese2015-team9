package ese.tutoragency.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ese.tutoragency.controller.pojos.SignupForm;
import ese.tutoragency.model.Member;

@Service
public class SampleServiceImpl implements SampleService {

	//@Autowired
	//MemberDao memberDao;

	@Transactional
	public SignupForm saveFrom(SignupForm signupForm) {
		String firstname = signupForm.getFirstname();
		String lastname = signupForm.getLastname();
		String email = signupForm.getEmail();
		Member user = new Member(firstname,lastname,email);

		// memberDao.save(user);

		/*
		 * String firstName = signupForm.getFirstName();
		 * 
		 * if(!StringUtils.isEmpty(firstName) &&
		 * "ESE".equalsIgnoreCase(firstName)) { throw new InvalidUserException(
		 * "Sorry, ESE is not a valid name"); // throw exception }
		 * 
		 * 
		 * Address address = new Address(); address.setStreet("TestStreet");
		 * 
		 * User user = new User(); user.setFirstName(signupForm.getFirstName());
		 * user.setEmail(signupForm.getEmail());
		 * user.setLastName(signupForm.getLastName()); user.setAddress(address);
		 * 
		 * address = addDao.save(address); user = userDao.save(user); // save
		 * object to DB
		 */

		// Iterable<Address> addresses = addDao.findAll(); // find all
		// Address anAddress = addDao.findOne((long)3); // find by ID

		// signupForm.setId(user.getId());

		return signupForm;

	}
}
