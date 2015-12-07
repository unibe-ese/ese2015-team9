package team9.tutoragency.controller.service;

import java.util.Optional;

import team9.tutoragency.model.Member;

public interface MemberService {

	/**
	 * This method loads the authenticated member.
	 * @return Optional.empty, if no authentication found, or an Optional,
	 *         wrapping the currently authenticated Member (fetched from the DB
	 *         by Id).
	 */
	Optional<Member> getAuthenticatedMember();
	
	/**
	 * @param memberId
	 *            mustn't be null
	 * @return An empty {@code Optional} when no {@code Member} with this
	 *         {@code id} was found. Else, an Optional wrapping the matching
	 *         {@code Member}.
	 * @throws AssertionError
	 *             thrown when id is null.
	 */
	Optional<Member> findById(Long id) throws AssertionError;
}
