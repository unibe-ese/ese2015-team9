package team9.tutoragency.controller.service;

import java.util.List;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.model.Offer;
/**
 * An implementation of this class provides a method to find {@code Offer}'s matching the criteria
 * specified in a {@code SearchForm}.
 * @see {@link SearchForm} 
 * @author bruno
 *
 */
public interface SearchService {

	/**
	 * Returns a List of {@link TutoringOffers} matching the specified criteria in the search form. 
	 * @param form - must be not null
	 * @return
	 */
	List<Offer> findOffers(SearchForm form);

}