package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.OfferDao;

@Service
public class OfferService {
	
	@Autowired OfferDao offerDao;
	@Autowired CourseDao courseDao;
	
	@Transactional
	public boolean removeOffer(Member member, Long courseId){
		assert courseId!=null;
		
		Course course = courseDao.findOne(courseId);
		
		if(course!= null && member!= null){
			List<Offer> offers = offerDao.findByMemberAndCourse(member, course);
			for(Offer offer: offers){
				offerDao.delete(offer);
			}
			return true;
		}
		return false;
	}
	
	@Transactional
	public boolean addOffer(Member member, Long courseId, float grade){
		assert courseId!=null;
		
		Course course = courseDao.findOne(courseId);
		
		if(course!= null && member!= null){
			offerDao.save(new Offer(member, course, grade));
			return true;
		}
		return false;
	}
	

	public List<String> getPossibleGrades() {
		List<String> gradeChoices = new ArrayList<String>();
		for (float i = 4; i <= 6; i += 0.25) {
			gradeChoices.add(Float.toString(i));
		}
		return gradeChoices;
	}

	public List<Offer> findByTutor(Member tutor) {
		return offerDao.findByMember(tutor);
	}
}

