package team9.tutoragency.controller.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired MemberDao memberDao;
	
	/* (non-Javadoc)
	 * @see team9.tutoragency.controller.service.AccountService#getAuthenticatedMember(team9.tutoragency.controller.pojos.SignupForm)
	 */
	@Override
	@Transactional
	public Optional<Member> getAuthenticatedMember() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication.getPrincipal().equals("anonymousUser"))
			return Optional.empty();
		else
			return Optional.of(memberDao.findOne(((Member) authentication.getPrincipal()).getId()));
	}

	/* (non-Javadoc)
	 * @see team9.tutoragency.controller.service.AccountService#findById(java.lang.Long)
	 */
	@Override
	@Transactional
	public Optional<Member> findById(Long memberId) throws AssertionError{
		if(memberId==null) throw new AssertionError("Member Id is Null");
		return Optional.ofNullable(memberDao.findOne(memberId));
	}

}
