package team9.tutoragency.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.Subscription;

public interface SubscriptionDao extends CrudRepository<Subscription, Long>{

	public List<Subscription> findByMemberAndOffer(Member member, Offer offer);

	public List<Subscription> findByOffer(Offer findOne);

}
