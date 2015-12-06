package team9.tutoragency.controller.service.impl;

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
import team9.tutoragency.controller.service.AccountService;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	MemberDao memberDao;
	@Autowired
	UniversityDao uniDao;

	/* (non-Javadoc)
	 * @see team9.tutoragency.controller.service.AccountService#upgradeAuthenticatedMemberToTutor()
	 */
	@Override
	@Transactional
	public Member upgradeMemberToTutor(Long memberId) {
		if(memberId==null) throw new AssertionError("Member Id is Null");
		if(!memberDao.exists(memberId)) throw new AssertionError("Member does not exist.");
		Member member = memberDao.findOne(memberId);

		if (member != null) {
			member.setIsTutor(true);
			memberDao.save(member);
		}

		return member;
	}

	/* (non-Javadoc)
	 * @see team9.tutoragency.controller.service.AccountService#saveEditChange(team9.tutoragency.model.Member, team9.tutoragency.controller.pojos.EditForm)
	 */
	@Override
	@Transactional
	public void updateAccount(Member member, EditForm editForm) {
		member.setEmail(editForm.getEmail());

		if (editForm.getPassword().length() > 0) {
			member.setPassword(DigestUtils.md5Hex(editForm.getPassword()));
		}
		member.setFirstName(editForm.getFirstName());
		member.setLastName(editForm.getLastName());
		member.setUsername(editForm.getUsername());
		member.setDescription(editForm.getDescription());

		if (member.isIsTutor()) {
			double fee = Double.parseDouble(editForm.getFee());
			member.setFee(fee);
			List<University> tmpList = new ArrayList<>();
			for (int i = 0; i < editForm.getUniversities().size(); i++) {
				List<University> selectedUni = uniDao.findByName(editForm.getUniversities().get(i));
				tmpList.add(selectedUni.get(0));
			}
			if (!tmpList.isEmpty())
				member.setUniversityList(tmpList);
		}
		memberDao.save(member);
	}

	/* (non-Javadoc)
	 * @see team9.tutoragency.controller.service.AccountService#createMember(team9.tutoragency.controller.pojos.SignupForm)
	 */
	@Override
	@Transactional
	public SignupForm createAccount(SignupForm signupForm) throws InvalidUserException {

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
