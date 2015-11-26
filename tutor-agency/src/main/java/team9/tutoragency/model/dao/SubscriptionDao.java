package team9.tutoragency.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.Subscription;

public interface SubscriptionDao extends CrudRepository<Subscription, Long>{

	public Subscription findByMemberAndOffer(Member member, Offer offer);

}
