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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import team9.tutoragency.controller.pojos.AddCourseForm;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.UniversityDao;

@Controller
public class CourseController {

	@Autowired
	UniversityDao uniDao;
	@Autowired
	CourseDao courseDao;
	@Autowired
	MemberDao memberDao;

	@RequestMapping(value = "/addCourse", method = RequestMethod.GET)
	public ModelAndView addCourse(HttpServletResponse response) throws IOException {
		ModelAndView addCourse = new ModelAndView("addCourse");
		addCourse.addObject("addCourseForm", new AddCourseForm());
		List<University> universities = Lists.newArrayList(uniDao.findAll());

		addCourse.addObject("universities", universities);
		addCourse.addObject("courses", courseDao.findByUniversity(universities.get(0)));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		addCourse.addObject("member", member);
		addCourse.addObject("unis",member.getUniversityList());
		return addCourse;
	}

	@RequestMapping(value = "/updateDropdown", method = RequestMethod.POST)
	public ModelAndView update(@Valid AddCourseForm addCourseForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		ModelAndView addCourse = new ModelAndView("addCourse");
		addCourse.addObject("addCourseForm", addCourseForm);
		List<University> universities = Lists.newArrayList(uniDao.findAll());
		University selectedUni = uniDao.findByName(addCourseForm.getSelectedUniversity()).get(0);
		universities.remove(selectedUni);
		universities.add(0, selectedUni); // set chosen university at beginning
											// of list
		addCourse.addObject("universities", universities);
		addCourse.addObject("courses", courseDao.findByUniversity(selectedUni));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		addCourse.addObject("member", member);
		addCourse.addObject("unis",member.getUniversityList());
		return addCourse;
	}

	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public String save(@Valid AddCourseForm addCourseForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		List<Course> courseList = member.getCourseList();
		if (courseList != null) {
			Course course = courseDao.findByName(addCourseForm.getSelectedCourse()).get(0);
			courseList.add(course);
			memberDao.save(member);
		}
		return "redirect:/profile";
	}
	

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showCourses(HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("showCourses");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		List<Course> courseList = member.getCourseList();
		model.addObject("courses", courseList);
		model.addObject("unis", member.getUniversityList());
		model.addObject("member", member);
		return model;
	}

	@RequestMapping(value = "/delete_{id}", method = RequestMethod.POST)
	public ModelAndView deleteCourse(HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		List<Course> courseList = member.getCourseList();
		for (int i = 0; i < courseList.size(); i++) {
			if (courseList.get(i).getId() == id) {
				courseList.remove(courseList.get(i));
			}
		}
		return showCourses(response);
	}
}
