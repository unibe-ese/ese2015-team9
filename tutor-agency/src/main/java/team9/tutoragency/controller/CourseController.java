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

import team9.tutoragency.controller.pojos.AddCourseForm;
import team9.tutoragency.controller.service.CourseService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;

@Controller
public class CourseController {

	@Autowired
	CourseService courseService;

	@RequestMapping(value = "/addCourse", method = RequestMethod.GET)
	public ModelAndView showAddCourseView(HttpServletResponse response) throws IOException {
		ModelAndView addCourse = new ModelAndView("addCourse");
		addCourse.addObject("addCourseForm", new AddCourseForm());
		courseService.generateAddCourseModel(addCourse);
		return addCourse;
	}

	@RequestMapping(value = "/updateDropdown", method = RequestMethod.POST)
	public ModelAndView updateDropdown(@Valid AddCourseForm addCourseForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		ModelAndView model = new ModelAndView("addCourse");
		courseService.updateDropdown(model, addCourseForm);
		return model;
	}

	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public String save(@Valid AddCourseForm addCourseForm, BindingResult result, RedirectAttributes redirectAttributes)
			throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		courseService.addCourseToMember(member, addCourseForm.getSelectedCourse());
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

		courseService.deleteProvidedCourse(member, id);

		return showCourses(response);
	}
}
