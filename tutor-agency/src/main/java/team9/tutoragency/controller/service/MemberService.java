package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.exceptions.InvalidUserException;
import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

@Service
public class MemberService {
	
	@Autowired
	MemberDao memberDao;
	@Autowired
	UniversityDao uniDao;

	@Transactional
	public Member upgradeAuthenticatedMemberToTutor() {

		Optional<Member> member = getAuthenticatedMember();

		if (member.isPresent()) {
			member.get().setIsTutor(true);
			memberDao.save(member.get());
		}

		return member.get();
	}

	@Transactional
	public Member findById(Long id) {
		return memberDao.findOne(id);
	}

	@Transactional
	public void saveEditChange(Member member, EditForm editForm) {
		member.setEmail(editForm.getEmail());

		if (editForm.getPassword().length() > 0) {
			member.setPassword(DigestUtils.md5Hex(editForm.getPassword()));
		}
		member.setFirstName(editForm.getFirstName());
		member.setLastName(editForm.getLastName());
		member.setUsername(editForm.getUsername());

		if (member.isIsTutor()) {
			double fee = Double.parseDouble(editForm.getFee());
			member.setFee(fee);
			List<University> tmpList = new ArrayList<University>();
			for (int i = 0; i < editForm.getUniversities().size(); i++) {
				List<University> selectedUni = uniDao.findByName(editForm.getUniversities().get(i));
				tmpList.add(selectedUni.get(0));
			}
			if (!tmpList.isEmpty())
				member.setUniversityList(tmpList);
		}
		memberDao.save(member);
	}

	/**
	 * Use this method instead of retrieving the authenticated member from the
	 * SecurityContext, because the lists might otherwise not correspond to the
	 * DB entities.
	 * 
	 * @return Optional.empty, if no authentication found, or an Optional,
	 *         wrapping the currently authenticated Member (fetched from the DB
	 *         by Id).
	 */
	@Transactional
	public Optional<Member> getAuthenticatedMember() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication.getPrincipal().equals("anonymousUser"))
			return Optional.empty();
		else
			return Optional.of(memberDao.findOne(((Member) authentication.getPrincipal()).getId()));
	}

	public List<Member> findByFee(int min, int max) {
		return memberDao.findByFeeBetween((double) min, (double) max);
	}

	@Transactional
	public SignupForm createMember(SignupForm signupForm) throws InvalidUserException {

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
