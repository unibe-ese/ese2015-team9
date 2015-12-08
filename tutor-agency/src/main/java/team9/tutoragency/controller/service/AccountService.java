package team9.tutoragency.controller.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.exceptions.InvalidUserException;
import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.model.Member;

/**
 * An implementation of this class provides methods to create or alter a {@code Member}.
 * @author bruno
 *
 */
public interface AccountService {

	/**
	 * Sets the @{@code isTutor} flag of the {@code Member} with the given {@code id}, to true.
	 * @param memberId mustn't be null and an {@code Member} with this id must exist.
	 * @throws AssertionError when id is null or no offer with this id exists.
	 */
	Member upgradeMemberToTutor(Long memberId);

	void updateAccount(Member member, EditForm editForm);

	SignupForm createAccount(SignupForm signupForm) throws InvalidUserException;

	
}