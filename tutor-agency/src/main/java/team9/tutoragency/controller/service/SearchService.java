package team9.tutoragency.controller.service;

import java.util.List;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.model.Offer;

public interface SearchService {

	/**
	 * Returns a List of {@link TutoringOffers} matching the specified criteria in the search form. 
	 * @param form - must be not null
	 * @return
	 */
	List<Offer> findOffers(SearchForm form);

}