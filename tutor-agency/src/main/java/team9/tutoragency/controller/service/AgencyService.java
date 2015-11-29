package team9.tutoragency.controller.service;

import java.util.List;
import java.util.Optional;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;

public interface AgencyService {

	public Optional<Offer> findOfferById(Long id);

	public void removeOffer(Long id);

	public void createOffer(Long memberId, Long courseId, float grade);

	public boolean isNewOffer(Long memberId, Long courseId);

	public void subscribeMemberToOffer(Long memberId, Long offerId);

	public void acceptSubscription(Long id);
}
