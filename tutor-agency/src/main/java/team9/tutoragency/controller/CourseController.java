package team9.tutoragency.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import team9.tutoragency.controller.pojos.AddCourseForm;
import team9.tutoragency.controller.service.CourseService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.OfferService;
import team9.tutoragency.controller.service.UniversityService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;

/**
 * The {@link CourseController} handles all interactions a registered
 * {@link Member} to add or remove an offered {@link Course}. The class
 * interacts with {@link CourseService} in order to write the changes
 * persistently to the database. For all methods the user has to be logged
 * in(get requests for the views are registered in the springSecurity.xml file).
 * 
 * @author laeri
 * @author bruno
 */
@Controller

public class CourseController {

	@Autowired
	CourseService courseService;

	@Autowired
	OfferService offerService;

	@Autowired
	MemberService memberService;

	@Autowired
	UniversityService uniService;

	@ModelAttribute
	public void universities(ModelMap modelMap) {
		if (!modelMap.containsAttribute("universities") || modelMap.get("universities") != null)
			modelMap.addAttribute("universities", uniService.findAll());
	}

	@ModelAttribute("member")
	public Member findMember() {
		return memberService.getAuthenticatedMember().get();
	}

	/**
	 * This method is called when a {@link Member} tries to offer a course as a
	 * tutor. The {@link #showAddCourseView(HttpServletResponse)} will add an
	 * {@link AddCourseForm} to the
	 * {@link #showAddCourseView(HttpServletResponse)} page where the Member can
	 * choose a course. This request is accessible from the user profile, if the
	 * user is not logged in, he is forwarded to the profile page
	 * (springSecurity.xml has an entry for /addCourse which is only accessible
	 * by a registered member).
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/addOffer", method = RequestMethod.GET)
	public ModelAndView showAddCourseView(HttpServletResponse response) {

		ModelAndView addCourse = new ModelAndView("addCourse");

		AddCourseForm form = new AddCourseForm();
		University preselectedUni = uniService.findAll().get(0);
		form.setSelectedUniversity(preselectedUni.getName());
		addCourse.addObject("addCourseForm", form);
		generateAddCourseModel(addCourse, preselectedUni);
		return addCourse;
	}

	/**
	 * Updates a dropdown list when a {@link Member} chooses a
	 * {@link University} in the addCourse view. If a university is chosen from
	 * the selection, the second selection should be updated in order to show
	 * only the courses belonging to the specified university.
	 * 
	 * @param addCourseForm
	 * @param result
	 * @param redirectAttributes
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateDropdown", method = RequestMethod.POST)
	public ModelAndView updateDropdown(@Valid AddCourseForm addCourseForm, BindingResult result,
			RedirectAttributes redirectAttributes, ModelAndView model) throws IOException {
		model.addObject("addCourseForm", addCourseForm);
		model.setViewName("addCourse");

		University selectedUni = uniService.findByName(addCourseForm.getSelectedUniversity()).get(0);

		model.addObject("courses", courseService.findByUniversity(selectedUni));
		Member member = memberService.getAuthenticatedMember().get();
		model.addObject("member", member);
		model.addObject("unis", member.getUniversityList());

		model.addObject("gradeChoices", Offer.grades());

		return model;
	}

	/**
	 * Tries to save a course with help of the {@link CourseService} when the
	 * user has chosen a course and submits the {@link AddCourseForm}.
	 * 
	 * @param addCourseForm
	 *            course which should be added to a {@link Member}
	 * @return redirects to the profile page
	 * @throws IOException
	 */
	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public ModelAndView saveCourse(@Valid AddCourseForm addCourseForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {

		Member member = memberService.getAuthenticatedMember().get();
		float grade = Float.parseFloat(addCourseForm.getGrade());

		offerService.addOffer(member, addCourseForm.getSelectedCourse(), grade);

		ModelAndView profile = new ModelAndView("redirect:/profile");
		return profile;
	}

	/**
	 * Handles a delete request from a {@link Member} when he/she wants to
	 * delete an offered course from the profile.
	 * 
	 * @param response
	 * @param id
	 *            id for the {@link Course} which should be removed from the
	 *            offered courses of the {@link Member}
	 * @return the {@link ModelAndView} of the showCourses page created by
	 *         {@link #showCourses(HttpServletResponse)}
	 * @throws IOException
	 */
	@RequestMapping(value = "/delete_{id}", method = RequestMethod.POST)
	public ModelAndView deleteCourse(HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
		Member member = memberService.getAuthenticatedMember().get();
		offerService.removeOffer(member, id);
		return new ModelAndView("redirect:/profile");
	}

	

	public void generateAddCourseModel(ModelAndView addCourse, University preselectedUni) {
		List<University> universities = Lists.newArrayList(uniService.findAll());
		addCourse.addObject("universities", universities);
		addCourse.addObject("courses", courseService.findByUniversity(preselectedUni));
		Member member = memberService.getAuthenticatedMember().get();
		// addCourse.addObject("member", member);
		addCourse.addObject("unis", member.getUniversityList());
		addCourse.addObject("gradeChoices", Offer.grades());
	}

}
