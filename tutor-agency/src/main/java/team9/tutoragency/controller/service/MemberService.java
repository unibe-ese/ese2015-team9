package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.pojos.EditForm;
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

	public void upgradeToTutor(Member member) {
		member.setIsTutor(true);
		memberDao.save(member);
	}
	@Transactional
	public Member findById(Long id){
		return memberDao.findOne(id);
	}
	@Transactional
	public void saveEditChange(Member member, EditForm editForm){
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

			member.setUniversityList(tmpList);
		}
		memberDao.save(member);
	}

	
}
