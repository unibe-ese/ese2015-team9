package team9.tutoragency.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import team9.tutoragency.controller.exceptions.InvalidUserException;
import team9.tutoragency.controller.pojos.AddCourseForm;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.pojos.searchForm;
import team9.tutoragency.controller.service.SampleService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

@Controller
public class SearchController {

	@Autowired
	UniversityDao uniDao;
	@Autowired
	CourseDao courseDao;
	@Autowired
	MemberDao memberDao;
	

    @RequestMapping(value = "/simpleSearch", method = RequestMethod.GET)
	public ModelAndView ssearch(HttpServletResponse response) throws IOException {
		ModelAndView ssearch = new ModelAndView("simpleSearch");
		ssearch.addObject("searchForm", new searchForm());
		List<University> universities = Lists.newArrayList(uniDao.findAll());

		ssearch.addObject("universities", universities);
		ssearch.addObject("courses", courseDao.findByUniversity(universities.get(0)));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		ssearch.addObject("member", member);
		ssearch.addObject("unis",member.getUniversityList());
		return ssearch;
	}
    
    
    
    
    @RequestMapping(value = "/asearch")
    public ModelAndView asearch() {
    	ModelAndView model = new ModelAndView("asearch");
        return model;
    }    
    
    @RequestMapping(value = "/updateSearchDropdown", method = RequestMethod.POST)
	public ModelAndView update(@Valid searchForm searchForm, BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
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
		model.addObject("unis",member.getUniversityList());
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
}


