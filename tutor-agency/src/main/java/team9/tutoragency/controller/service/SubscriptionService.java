package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.Subscription;
import team9.tutoragency.model.dao.SubscriptionDao;
@Service
public class SubscriptionService {

	@Autowired
	SubscriptionDao subscriptionDao;
	
	@Transactional(readOnly = true)
	public List<Subscription> findByMember(Member member){
		if(member == null)
			return new ArrayList<Subscription>();
		else
			return subscriptionDao.findByMember(member);
	}
	
	@Transactional(readOnly = true)
	public Optional<Subscription> findOne(Member member, Offer offer) throws DuplicateKeyException{
		if(member == null || offer == null)
			return Optional.empty();
		//else
		List<Subscription> subscriptions = subscriptionDao.findByMemberAndOffer(member, offer);
		
		if(subscriptions == null || subscriptions.isEmpty())
			return Optional.empty();
		
		if(subscriptions.size()>1)
			throw new DuplicateKeyException("subscription duplicate found");
		else
			return Optional.of(subscriptions.get(0));
	}

	@Transactional
	public void save(Subscription entity) {
		//TODO should check for duplicates
		subscriptionDao.save(entity);
	}

	public void acceptSubscriptionIfFromAuthMember(Long id) {
		Subscription entity = subscriptionDao.findOne(id);
		if(entity != null){
			entity.setAccepted(true);
			subscriptionDao.save(entity);
		}
			
		
	}
}
