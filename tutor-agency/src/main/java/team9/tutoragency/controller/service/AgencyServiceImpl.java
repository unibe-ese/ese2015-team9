package team9.tutoragency.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.OfferDao;
import team9.tutoragency.model.dao.UniversityDao;
@Service
public class AgencyServiceImpl implements AgencyService{

	@Autowired UniversityDao uniDao;
	@Autowired CourseDao courseDao;
	@Autowired OfferDao offerDao;
	@Autowired MemberDao memberDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<University> findAllUniversities() {
		return uniDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Course> findCoursesByUniversity(University university) {
		assert university != null;
		return courseDao.findByUniversity(university);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Offer> findOfferById(Long id) {
		assert id != null;
		return Optional.ofNullable(offerDao.findOne(id));
	}

	@Override
	@Transactional
	public void removeOffer(Long id) {
		assert id != null;
		assert offerDao.exists(id);
		
		offerDao.delete(id);
		
	}

	@Override
	@Transactional
	public void createOffer(Long memberId, Long courseId, float grade) {
		assert memberId != null;
		assert courseId != null;
		assert memberDao.exists(memberId);
		assert courseDao.exists(courseId);
		
		Offer offer = new Offer(memberDao.findOne(memberId), courseDao.findOne(courseId), grade);
		
		assert isNew(offer);
		
		offerDao.save(offer);
	}

	@Transactional(readOnly=true)
	public boolean isNewOffer(Long memberId, Long courseId){
		assert memberId!= null;
		assert courseId!= null;
		
		Member member = memberDao.findOne(memberId);
		Course course = courseDao.findOne(courseId);
		
		assert member != null;
		assert course != null;
		
		if(offerDao.findByTutorAndCourse(member, course).isEmpty())
			return true;
		else 
			return false;
	}
	
	@Transactional(readOnly=true)
	private boolean isNew(Offer offer) {
		if(offerDao.findByTutorAndCourse(offer.getTutor(), offer.getCourse())==null)
			return true;
		else
			return false;
	}

	@Override
	public Optional<University> findUniById(Long uniId) {
		assert uniId != null;
		return Optional.ofNullable(uniDao.findOne(uniId));
	}

}
