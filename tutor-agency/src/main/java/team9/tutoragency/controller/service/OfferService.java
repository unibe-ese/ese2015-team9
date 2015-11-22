package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	@Autowired MemberService memberService;
	
	@Transactional
	public boolean removeOffer(Member member, Long courseId){
		assert courseId!=null;
		
		Course course = courseDao.findOne(courseId);
		
		if(course!= null && member!= null){
			List<Offer> offers = offerDao.findByTutorAndCourse(member, course);
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
		return offerDao.findByTutor(tutor);
	}

	@Transactional
	public void subscribeAuthMemberToOffer(Long offerId) {
		Optional<Member> member = memberService.getAuthenticatedMember();
		
		if(member.isPresent() || offerDao.exists(offerId)){
			Offer offer = offerDao.findOne(offerId);
			List<Member> subscribers = offer.getSubscribers();
			if(subscribers == null){
				subscribers = new ArrayList<Member>();
				subscribers.add(member.get());
			} else {
				if(!subscribers.contains(member.get()))
					subscribers.add(member.get());
					
			}
			offer.setSubscribers(subscribers);
			offerDao.save(offer);
			
		}
			
			
	}
}

