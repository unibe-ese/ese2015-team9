package team9.tutoragency.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import team9.tutoragency.controller.service.AccountService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.impl.AccountServiceImpl;
import team9.tutoragency.model.Member;
/**
 * Superclass for Controllers who need to get the Authenticated Member, and mustn't be invoked when he is not present.
 * @author bruno
 *
 */
public abstract class AutenthicatedAccessController {

	@Autowired
	protected MemberService memberService;
	
	/**
	 * @return The authenticated Member - never null
	 * @throws AssertionError when no authenticated member could be loaded.
	 */
	protected Member getAuthenticatedMember() throws AssertionError{
		Optional<Member> member = memberService.getAuthenticatedMember();
		
		if(!member.isPresent())
			throw new AssertionError("The URL should have been intercepted by Spring Security!");
		
		return member.get();
		
	}
}
