package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.exceptions.InvalidValueException;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.Subscription;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.OfferDao;
import team9.tutoragency.model.dao.SubscriptionDao;

@Service
public class OfferService {
	
	@Autowired OfferDao offerDao;
	@Autowired CourseDao courseDao;
	@Autowired MemberService memberService;
	@Autowired SubscriptionService subscriptionService;
	
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
	
	public List<Offer> findByTutor(Member tutor) {
		return offerDao.findByTutor(tutor);
	}

	/**
	 * Invokes the query method findByCourseIn(courses) of the offer dao. 
	 * Prevents the query from being invoked with an empty or null course list.
	 * @param courses - If null or empty an empty List is returned. 
	 */
	@Transactional(readOnly = true)
	public List<Offer> findByCourses(Collection<Course> courses){
		if(courses == null || courses.isEmpty())
			return new ArrayList<Offer>();
		else
			return offerDao.findByCourseIn(courses);
	}
	
	@Transactional
	public void subscribeAuthMemberToOffer(Long offerId) {
		Optional<Member> member = memberService.getAuthenticatedMember();
		Offer offer = offerDao.findOne(offerId);
		Optional<Subscription> sub = subscriptionService.findOne(member.get(), offer);
		
		if(member.isPresent() && offer!= null && !sub.isPresent()){
			Subscription entity = new Subscription(member.get(), offer);
			subscriptionService.save(entity);			
		}	
	}

	/**
	 * Returns a List of offers , either obtained from {@code OfferDao}'s {@code findByGradeGreaterThanEqual} query method, if members or courses is null. Or else the {@code findByTutorInAndCourseInAndGradeGreaterThanEqual} query. 
	 * Prevents the queries from being invoked with an empty or null parameters.
	 * Asserts minGrade not null.
	 * @param members - if null treated as empty list.
	 * @param courses - if null treated as empty list.
	 * @param minGrade must be not null
	 * 
	 * @throws NumberFormatException  if the minGrade does not contain a parsable float.
	 */
	@Transactional(readOnly=true)
	public List<Offer> findByTutorsCoursesAndGrades(List<Member> members, List<Course> courses, String minGrade) throws NumberFormatException{
		assert minGrade != null; 
		
		float grade = parseGrade(minGrade);
		
		if(members == null || courses == null || members.isEmpty() || courses.isEmpty())
			return offerDao.findByGradeGreaterThanEqual(grade);
		else
			return offerDao.findByTutorInAndCourseInAndGradeGreaterThanEqual(members, courses,grade);
	}

	private float parseGrade(String gradeAsString) throws NumberFormatException{
		return Float.parseFloat(gradeAsString);		
	}
}

