package team9.tutoragency.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.Subscription;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.OfferDao;
import team9.tutoragency.model.dao.SubscriptionDao;
import team9.tutoragency.model.dao.UniversityDao;

/**
 * Service for all the "agency" tasks. Meaning everything concerning offers, and subscriptions.
 * @author brn
 *
 */
@Service
public class AgencyServiceImpl implements AgencyService{

	@Autowired UniversityDao uniDao;
	@Autowired CourseDao courseDao;
	@Autowired OfferDao offerDao;
	@Autowired MemberDao memberDao;
	@Autowired SubscriptionDao subscriptionDao;
	
	/**
	 * Finds the offer with the given Id.
	 * <p>
	 * <b>Asserts that:<b>
	 * <li>id is not null</li>
	 * </p>
	 * @return empty Optional if offer does not exist.
	 */
	@Override
	@Transactional(readOnly=true)
	public Optional<Offer> findOfferById(Long offerId) {
		if (offerId==null) throw new AssertionError("offer id is null!");
		return Optional.ofNullable(offerDao.findOne(offerId));
	}

	/**
	 * Deletes the offer with the given id.
	 * <p>
	 * <b>Asserts that:<b>
	 * <li>id is not null</li>
	 * <li>offer with given id exists</li>
	 * </p>
	 */
	@Override
	@Transactional
	public void removeOffer(Long offerId) {
		if (offerId==null) throw new AssertionError("offer id is null!");
		if (!offerDao.exists(offerId)) throw new AssertionError("Offer does not exist!");
		
		subscriptionDao.delete(subscriptionDao.findByOffer( offerDao.findOne(offerId)));
		offerDao.delete(offerId);
		
	}

	/**
	 * Creates an Offer with the passed parameters. And saves it in the Database.
	 * <p>
	 * <b>Asserts that:<b>
	 * <li>memberId is not null</li>
	 * <li>member with given id exists</li>
	 * <li>courseId is not null</li>
	 * <li>course with given id exists</li>
	 * <li>offer with member and course does not exist</li>
	 * </p>
	 */
	@Override
	@Transactional
	public void createOffer(Long memberId, Long courseId, float grade) {
		if (memberId==null) throw new AssertionError("memberId is NULL");
		if (courseId==null) throw new AssertionError("courseId is NULL");
		if (!memberDao.exists(memberId)) throw new AssertionError("Member does not exist!");
		if (!courseDao.exists(courseId)) throw new AssertionError("Course does not exist!");
			
		Offer offer = new Offer(memberDao.findOne(memberId), courseDao.findOne(courseId), grade);
		
		if (!isNew(offer)) throw new AssertionError("Offer [" + offer.getTutor().getUsername() + ", "+ offer.getCourse().getName() +"] already exists!");
		
		offerDao.save(offer);
	}

	/**
	 * Returns true if no offer with the given member and course exists.
	 * <p>
	 * <b>Asserts that:<b>
	 * <li>memberId is not null</li>
	 * <li>member with given id exists</li>
	 * <li>courseId is not null</li>
	 * <li>course with given id exists</li>
	 * </p>
	 */
	@Transactional(readOnly=true)
	public boolean isNewOffer(Long memberId, Long courseId){
		if (memberId==null) throw new AssertionError("memberId is NULL");
		if (courseId==null) throw new AssertionError("courseId is NULL");
		if (!memberDao.exists(memberId)) throw new AssertionError("Member does not exist!");
		if (!courseDao.exists(courseId)) throw new AssertionError("Course does not exist!");
		
		Member member = memberDao.findOne(memberId);
		Course course = courseDao.findOne(courseId);
		
		List<Offer> matches = offerDao.findByTutorAndCourse(member, course);
		if(matches==null || matches.isEmpty())
			return true;
		else 
			return false;
	}
	
	private boolean isNew(Offer offer) {
		return isNewOffer(offer.getTutor().getId(), offer.getCourse().getId());
	}

	@Override
	public void subscribeMemberToOffer(Long memberId, Long offerId) {
		if (memberId==null) throw new AssertionError("memberId is NULL");
		if (offerId==null) throw new AssertionError("offerId is NULL");
		if (!memberDao.exists(memberId)) throw new AssertionError("Member does not exist!");
		if (!offerDao.exists(offerId)) throw new AssertionError("Offer does not exist!");
		
		Member member = memberDao.findOne(memberId);
		Offer offer = offerDao.findOne(offerId);
		
		if(subscriptionDao.findByMemberAndOffer(member, offer).isEmpty()){
			Subscription entity = new Subscription(member, offer);
			subscriptionDao.save(entity);			
		}	
	}

	@Override
	public void acceptSubscription(Long subscriptionId) {
		if (subscriptionId==null) throw new AssertionError("subscription id is NULL");
		
		Subscription entity = subscriptionDao.findOne(subscriptionId);
		if(entity != null){
			entity.setAccepted(true);
			subscriptionDao.save(entity);
		}	
	}
}
