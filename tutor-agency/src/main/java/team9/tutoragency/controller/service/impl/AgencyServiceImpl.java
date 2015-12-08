package team9.tutoragency.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.service.AgencyService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.Subscription;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.OfferDao;
import team9.tutoragency.model.dao.SubscriptionDao;
import team9.tutoragency.model.dao.UniversityDao;

/**
 * Implementation of {@code AgencyService}.
 * @see {@linkplain AgencyService}.
 * @author bruno
 */
@Service
public class AgencyServiceImpl implements AgencyService{

	@Autowired UniversityDao uniDao;
	@Autowired CourseDao courseDao;
	@Autowired OfferDao offerDao;
	@Autowired MemberDao memberDao;
	@Autowired SubscriptionDao subscriptionDao;
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Offer> findOffer(Long offerId) throws AssertionError{
		if (offerId==null) throw new AssertionError("offer id is null!");
		return Optional.ofNullable(offerDao.findOne(offerId));
	}

	@Override
	@Transactional
	public void removeOffer(Long offerId) throws AssertionError{
		if (offerId==null) throw new AssertionError("offer id is null!");
		if (!offerDao.exists(offerId)) throw new AssertionError("Offer does not exist!");
		
		subscriptionDao.delete(subscriptionDao.findByOffer( offerDao.findOne(offerId)));
		offerDao.delete(offerId);
		
	}

	@Override
	@Transactional
	public void createOffer(Long memberId, Long courseId, float grade) throws AssertionError{
		if (memberId==null) throw new AssertionError("memberId is NULL");
		if (courseId==null) throw new AssertionError("courseId is NULL");
		if (!memberDao.exists(memberId)) throw new AssertionError("Member does not exist!");
		if (!courseDao.exists(courseId)) throw new AssertionError("Course does not exist!");
			
		Offer offer = new Offer(memberDao.findOne(memberId), courseDao.findOne(courseId), grade);
		
		if (!isNew(offer)) throw new AssertionError("Offer [" + offer.getTutor().getUsername() + ", "+ offer.getCourse().getName() +"] already exists!");
		
		offerDao.save(offer);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isNewOffer(Long memberId, Long courseId)throws AssertionError{
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
	public void createSubscription(Long memberId, Long offerId) throws AssertionError{
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
	public void acceptSubscription(Long subscriptionId) throws AssertionError{
		if (subscriptionId==null) throw new AssertionError("subscription id is NULL");
		
		Subscription entity = subscriptionDao.findOne(subscriptionId);
		
		if(entity == null) throw new AssertionError("subscription with id "+ subscriptionId +" does not exist!");
			
		entity.setAccepted(true);
		subscriptionDao.save(entity);
		
	}
}
