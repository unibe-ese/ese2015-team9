package team9.tutoragency.controller.service;

import java.util.Optional;

import team9.tutoragency.model.Offer;
import team9.tutoragency.model.Subscription;

/**
 * An implementation of this interface gives performs the "agency"-tasks.
 * This includes creating and removing {@code Offer}'s and creating and accepting {@code Subscription}'s. 
 * @see {@link Offer}, {@link Subscription}
 * 
 * @author bruno
 *
 */
public interface AgencyService {

	/**
	 * @param offerId
	 *            mustn't be null.
	 * @return An empty {@code Optional} when no Offer with this was found.
	 *         Else, an Optional wrapping the matching {@code Offer}.
	 * @throws AssertionError
	 *             thrown when offerId is null.
	 */
	public Optional<Offer> findOffer(Long offerId) throws AssertionError;

	/**
	 * Deletes the {@code Offer} with the given {@code offerId}, along with all {@code Subscription}'s for this {@code Offer}.
	 * @param offerId mustn't be null and an {@code Offer} with this id must exist.
	 * @throws AssertionError when id is null or no offer with this id exists.
	 */
	public void removeOffer(Long offerId) throws AssertionError;

	/**
	 * Creates and saves an {@code Offer} with the passed parameters. 
	 * @param memberId mustn't be null, and a {@code Member} with this id must exist.
	 * @param courseId mustn't be null, and a {@code Course} with this id must exist.
	 * @param grade 
	 * @throws AssertionError thrown when one of the upper conditions fails or if {@code !isNewOffer(memberId, courseId)}.
	 */
	public void createOffer(Long memberId, Long courseId, float grade )throws AssertionError;

	/**
	 * @param memberId mustn't be null, and a {@code Member} with this id must exist.
	 * @param courseId mustn't be null, and a {@code Course} with this id must exist.
	 * @throws AssertionError thrown when one of the upper conditions is violated.
	 */
	public boolean isNewOffer(Long memberId, Long courseId) throws AssertionError;

	/**
	 * Creates and saves an {@code Subscription} with the passed parameters. 
	 * @param memberId mustn't be null, and a {@code Member} with this id must exist.
	 * @param offerId mustn't be null, and a {@code Offer} with this id must exist.
	 * @throws AssertionError thrown when one of the upper conditions is violated.
	 */
	public void createSubscription(Long memberId, Long offerId) throws AssertionError;

	/**
	 * Sets {@code accepted} property in {@code Subscription} true.
	 * @param subscriptionId mustn't be null and {@code Subscription} with this id must exist.
	 * @throws AssertionError thrown when one of the upper conditions is violated.
	 */
	public void acceptSubscription(Long subscriptionId) throws AssertionError;
}
