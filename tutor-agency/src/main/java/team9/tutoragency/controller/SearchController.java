package team9.tutoragency.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.pojos.QuickSearchForm;
import team9.tutoragency.controller.pojos.SearchResult;
import team9.tutoragency.controller.service.SearchService;
import team9.tutoragency.controller.service.UniversityAccessService;

/**
 * This Controller handles page request related to search mechanisms.
 */
@Controller
public class SearchController {

	@Autowired
	SearchService searchService;
	@Autowired 
	UniversityAccessService uniService;
		
	@RequestMapping(value="/searchresults", method = RequestMethod.POST)
	public ModelAndView showQuickSearchResults(SearchForm searchForm, BindingResult result) {
		ModelAndView model = new ModelAndView("search");
		model.addObject("universities", uniService.findAllNames());
		model.addObject("searchResults", searchService.findCoursesByNameContaining(searchForm));
		model.addObject("form", new SearchForm());
		return model;
	}
	
	/**
	 * This method interprets the parameter "text" as course name and adds the results from {@link SearchService#findCoursesByNameContaining(String)} to the model. Also objects to allow a new search are added to the model.
	 * @param text
	 * @return 
	 */
	@RequestMapping(path="/quicksearch{text}", method=RequestMethod.GET)
	public ModelAndView quicksearch(@RequestParam String text) {
		ModelAndView model = new ModelAndView("search");
		List<SearchResult> results = searchService.findCoursesByNameContaining(text);
		System.out.println("text:"+text);
		model.addObject("searchResults", results);
		model.addObject("universities", uniService.findAllNames());
		model.addObject("form", new SearchForm());
		return model;
	}
	/**
	 * Returns a modelAndView with search.jsp as view, and a new {@link SearchForm}, but <b>no</b> {@link SearchResult}'s added as attributes to the model.
	 * @return
	 */
	@RequestMapping(path="/search", method=RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView model = new ModelAndView("search");
		model.addObject("universities", uniService.findAllNames());
		model.addObject("form", new SearchForm());
		return model;
	}
	

}
