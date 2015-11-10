package team9.tutoragency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team9.tutoragency.controller.pojos.QuickSearchForm;
import team9.tutoragency.controller.service.SearchService;

/**
 * This Controller handles page request related to search mechanisms.
 */
@Controller
public class SearchController {

	@Autowired
	SearchService searchService;

	/**
	 * This method returns a model with the quicksearch view adds the
	 * {@link SearchResult}'s from the {@link SearchService}'s
	 * findCoursesByNameContaining method.
	 * 
	 * @param searchForm
	 *            - must be not null (unknown consequences if null)
	 * @param result
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/showResults", method = RequestMethod.POST)
	public ModelAndView showQuickSearchResults(QuickSearchForm searchForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("quicksearch");
		model.addObject("searchResults", searchService.findCoursesByNameContaining(searchForm.getSearchText()));
		model.addObject("searchForm", new QuickSearchForm());
		return model;
	}

	/**
	 * This method returns the quicksearch view in a model without any
	 * {@link SearchResult}'s set.
	 * 
	 * @return modelAndView, with quicksearch as view.
	 */
	@RequestMapping(value = "/quicksearch", method = RequestMethod.GET)
	public ModelAndView showQuickSearch() {
		ModelAndView model = new ModelAndView("quicksearch");
		model.addObject("searchForm", new QuickSearchForm());
		
		return model;
	}

}
