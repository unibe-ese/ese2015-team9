package team9.tutoragency.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.service.OfferService;
import team9.tutoragency.controller.service.SearchService;
import team9.tutoragency.controller.service.UniversityService;
import team9.tutoragency.model.Offer;

/**
 * This Controller handles page request related to search mechanisms.
 * @author bruno
 */
@Controller
public class SearchController {

	@Autowired
	SearchService searchService;
	@Autowired 
	UniversityService uniService;
	
	
	/**
	 * This method is invoked before any @RequestMapping - handler method is invoked.
	 * Also possible grades and university names are added for the search filter.
	 * @param text, if not null it is interpreted as course name for the quicksearch, and search results are added to the model. 
	 * @param model
	 */
	@ModelAttribute
	public void addAttributes(@RequestParam(value="text", required = false) String text, Model model){		
		
		List<Offer> offers = new ArrayList<Offer>();
		
		if(text!=null){
			offers = searchService.findOffers(new SearchForm(text));
		}
		
		model.addAttribute("offers", offers);
		model.addAttribute("universities", uniService.findAllNames());
		model.addAttribute("grades", Offer.grades());
	}
	
	/**
	 * Method to submit a search form via post request.
	 * @param form
	 * @return
	 */
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public ModelAndView submit(SearchForm form) {
		ModelAndView model = new ModelAndView("search");
		model.addObject("offers", searchService.findOffers(form));
		model.addObject("form", form);
		return model;
	}
	
	/**
	 * Method to get the search page.
	 * Returns a modelAndView with search.jsp as view, and a new {@link SearchForm} as Object in the Model.
	 * @return
	 */
	@RequestMapping(path="/search", method=RequestMethod.GET)
	public ModelAndView search(@RequestParam(value="text", required=false) String text) {
		ModelAndView model = new ModelAndView("search");
		model.addObject("form", new SearchForm(text));
		return model;
	}
	

}
