package team9.tutoragency.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import team9.tutoragency.controller.pojos.OfferForm;
import team9.tutoragency.controller.service.AgencyService;
import team9.tutoragency.controller.service.BasicDataService;
import team9.tutoragency.controller.service.MemberService;

import team9.tutoragency.controller.service.validation.OfferFormValidator;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;

/**
 * The {@link CreateOfferController} handles all interactions a registered
 * {@link Member} to add an {@code Offer}. The class interacts with
 * {@link CourseServiceImpl} in order to write the changes persistently to the
 * database. For all methods the user has to be logged in(get requests for the
 * views are registered in the springSecurity.xml file).
 * 
 * @author laeri
 * @author bruno
 */
@Controller
@RequestMapping(value = "/auth/offer/")
public class CreateOfferController extends AutenthicatedAccessController{

	@Autowired
	BasicDataService dataService;
	@Autowired
	AgencyService service;
	@Autowired
	OfferFormValidator validator;
	

	/**
	 * This method is invoked before any other handler method of this
	 * controller. Adds the objects needed to populate the selections in the. A
	 * list of all universities, the possible grades, the selected university
	 * and the courses from the selected university are added to the model. The
	 * selected university is either the one with the id passed as parameter or
	 * the first from the university list.
	 * 
	 * @param uniId
	 *            If null, the first university of the list is selected.
	 * @param modelMap
	 */
	@ModelAttribute
	public void populateModel(@RequestParam(value = "id", required = false) Long uniId, ModelMap modelMap) {
		List<University> unis = dataService.findAllUniversities();

		if (unis.isEmpty())
			throw new AssertionError("No Universities in Database!");

		University selectedUni = new University();

		if (uniId == null)
			selectedUni = unis.get(0);
		else
			selectedUni = dataService.findUniversity(uniId).orElse(unis.get(0));

		List<Course> courses = dataService.findCoursesByUniversity(selectedUni);

		modelMap.addAttribute("universities", unis);
		modelMap.addAttribute("courses", courses);
		modelMap.addAttribute("selectedUniversity", selectedUni);
		modelMap.addAttribute("grades", Offer.possibleGrades());
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView createOfferForm() {
		ModelAndView model = new ModelAndView("createOffer");
		model.addObject("offerForm", new OfferForm());
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView submitOfferForm(@ModelAttribute University selectedUniversity, OfferForm offerForm,
			BindingResult result) {
		Member member = super.getAuthenticatedMember();
		offerForm.setMemberId(member.getId());

		validator.validate(offerForm, result);

		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("createOffer", "offerForm", offerForm);

			Optional<Course> course = dataService.findCourse(offerForm.getCourseId());
			if (course.isPresent()){
				model.addObject("selectedUniversity", course.get().getUniversity());
				model.addObject("selectedCourse", course.get());
				model.addObject("courses", dataService.findCoursesByUniversity(course.get().getUniversity()));
			}
			return model;
		}
		// else
		service.createOffer(member.getId(), offerForm.getCourseId(), Float.parseFloat(offerForm.getGrade()));

		ModelAndView model = new ModelAndView("redirect:../account");
		model.addObject("message", "You have successfully created an offer. ");
		return model;
	}
}
