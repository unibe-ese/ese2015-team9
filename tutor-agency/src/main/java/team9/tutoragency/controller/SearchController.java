package team9.tutoragency.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import team9.tutoragency.controller.pojos.QuickSearchForm;
import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.service.SearchService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

@Controller
public class SearchController {

	@Autowired
	SearchService searchService;
	
	
	@RequestMapping(value = "/quickResults", method = RequestMethod.POST)
	public ModelAndView showQuickSearchResults(QuickSearchForm searchForm, BindingResult result, RedirectAttributes redirectAttributes){
		ModelAndView model = new ModelAndView("quicksearch");
		
		model.addObject("searchResults", searchService.findCoursesByNameContaining(searchForm.getSearchText()));

		
		model.addObject("searchForm", new QuickSearchForm());
		return model;
	}
	@RequestMapping(value = "/quicksearch", method = RequestMethod.GET)
	public ModelAndView showQuickSearch(){
		ModelAndView model = new ModelAndView("quicksearch");
		model.addObject("searchForm", new QuickSearchForm());
		return model;
	}
	
	/*UniversityDao uniDao;
	@Autowired
	CourseDao courseDao;
	@Autowired
	MemberDao memberDao;*/
/*
	@RequestMapping(value = "/simpleSearch", method = RequestMethod.GET)
	public ModelAndView ssearch(HttpServletResponse response) throws IOException {
		ModelAndView ssearch = new ModelAndView("simpleSearch");
		ssearch.addObject("searchForm", new SearchForm());
		List<University> universities = Lists.newArrayList(uniDao.findAll());

		ssearch.addObject("universities", universities);
		ssearch.addObject("courses", courseDao.findByUniversity(universities.get(0)));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		ssearch.addObject("member", member);
		ssearch.addObject("unis", member.getUniversityList());
		return ssearch;
	}

	@RequestMapping(value = "/asearch")
	public ModelAndView asearch() {
		ModelAndView model = new ModelAndView("asearch");
		return model;
	}

	@RequestMapping(value = "/updateSearchDropdown", method = RequestMethod.POST)
	public ModelAndView update(@Valid SearchForm searchForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		ModelAndView model = new ModelAndView("simpleSearch");
		model.addObject("searchForm", searchForm);
		List<University> universities = Lists.newArrayList(uniDao.findAll());
		University selectedUni = uniDao.findByName(searchForm.getSelectedUniversity()).get(0);
		universities.remove(selectedUni);
		universities.add(0, selectedUni); // set chosen university at beginning
											// of list
		model.addObject("universities", universities);
		model.addObject("courses", courseDao.findByUniversity(selectedUni));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		model.addObject("member", member);
		model.addObject("unis", member.getUniversityList());
		return model;
	}

	@RequestMapping(value = "/searchCourse", method = RequestMethod.GET)
	public ModelAndView showSearch(HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("searchCourse");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		List<Course> courseList = member.getCourseList();
		model.addObject("courses", courseList);
		model.addObject("unis", member.getUniversityList());
		model.addObject("member", member);
		return model;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView showSearchView(HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("search");
		SearchForm form = new SearchForm();
		model.addObject("searchForm", form);
		return model;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView showSearchResult(@Valid SearchForm searchForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		ModelAndView model = new ModelAndView("search");

		List<Member> members = Lists.newArrayList(memberDao.findAll());
		List<SearchResult> searchResult = new ArrayList<SearchResult>();
		for (Member tmpMember : members) {
			for (Course course : tmpMember.getCourseList()) {
				if (course.getName().contains(searchForm.getSearchText())) {
					searchResult.add(new SearchResult(course, tmpMember));
				}
			}
		}

		SearchForm form = new SearchForm();
		model.addObject("searchForm", form);
		model.addObject("searchResult", searchResult);
		return model;
	}
*/
	
}
