package team9.tutoragency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.service.BasicDataService;
import team9.tutoragency.controller.service.SearchService;
import team9.tutoragency.model.Offer;

/**
 * This Controller handles page request related to search mechanisms.
 * 
 * @author bruno
 */
@Controller
public class SearchController {

	@Autowired
	SearchService searchService;
	@Autowired
	BasicDataService dataService;

	/**
	 * This method is invoked before any @RequestMapping and adds the filter
	 * data for the filter options to the model.
	 */
	@ModelAttribute
	public void setFilterOptions(ModelMap model) {

		model.addAttribute("universities", dataService.findAllUniversities());
		model.addAttribute("grades", Offer.possibleGrades());
	}

	/**
	 * Method to submit a search form via post request.
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView showSearchResults(SearchForm form) {
		ModelAndView model = new ModelAndView("searchPage");
		model.addObject("offers", searchService.findOffers(form));
		model.addObject("form", form);
		return model;
	}

	/**
	 * Method to get the search page. Returns a modelAndView with searchPage.jsp as
	 * view, and a new {@link SearchForm} as Object in the Model.
	 */
	@RequestMapping(path = "/search", method = RequestMethod.GET)
	public ModelAndView getSearchPage() {
		ModelAndView model = new ModelAndView("searchPage");
		
		SearchForm form = new SearchForm();
		
		model.addObject("form", form);
		return model;
	}

}
