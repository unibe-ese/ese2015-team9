package team9.tutoragency.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.controller.service.BasicDataService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.validation.EditFormValidator;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;

/**
 * Handles requests for showing, and editing the account.
 * The URL is intercepted by spring security if no member is logged in.
 * 
 * @author laeri
 * @author bruno
 *
 */
@Controller
@RequestMapping(value = "/auth/account")
public class AccountController extends AutenthicatedAccessController{

	@Autowired
	EditFormValidator validator;
	@Autowired
	BasicDataService dataService;

	/**
	 * Asserts that the request token is authenticated (authenticated member is
	 * present).
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView showProfile(@RequestParam(value = "message", required = false) String message) {
		ModelAndView profile = new ModelAndView("profile");

		Member member = getAuthenticatedMember();

		profile.addObject("member", member);
		if(message!= null)
			profile.addObject("message", message);
		
		return profile;
	}

	

	/**
	 * Prepares the model for the edit view when a {@link Member} would like to
	 * edit his profile informations. The {@link EditForm} will contain all
	 * values of a {@link Member} to be edited.
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getEditPage() throws IOException {

		ModelAndView edit = new ModelAndView("edit");

		Member member = getAuthenticatedMember();

		EditForm editForm = new EditForm(member);

		List<String> universityNames = dataService.findAllUniversityNames();
		List<String> alreadySelectedUniversities = new ArrayList<String>();
		for(University uni: member.getUniversityList()){
			alreadySelectedUniversities.add(uni.getName());
		}
		edit.addObject("universityChoices", universityNames);
		edit.addObject("editForm", editForm);
		edit.addObject("member", member);
		edit.addObject("alreadySelectedUniversities", alreadySelectedUniversities);
		return edit;
	}

	/**
	 * Handles saving an {@link EditForm} with the help of the
	 * {@link MemberService} to the database. The {@link EditForm} is validated
	 * by the {@link EditFormValidator}. If the validation passes the
	 * {@link MemberService} saves the change persistently, if not the edit view
	 * is again displayed containing any errors.
	 * 
	 * @param editForm
	 *            Form which holds member informations which can be altered by a
	 *            {@link Member}
	 * @return The profile model for the profile view otherwise the edit page
	 *         will be shown again
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveChange(@Valid EditForm editForm, BindingResult result) throws IOException {

		ModelAndView model;
		validator.validate(editForm, result);

		Member member = getAuthenticatedMember();

		if (!result.hasErrors()) {

			memberService.saveEditChange(member, editForm);
			model = showProfile("You have successfully changed your profile information.");

		} else {
			editForm.setOldPassword("");
			
			model = new ModelAndView("edit", "editForm", editForm);

			List<String> universityNames = dataService.findAllUniversityNames();

			model.addObject("universityChoices", universityNames);
			model.addObject("member", member);
		}
		return model;
	}

	/**
	 * Upgrades a tutor with the help of the {@link MemberService} if a user
	 * clicks the "werde Tutor" button.
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/becomeTutor", method = RequestMethod.POST)
	public ModelAndView becomeTutor() {

		memberService.upgradeAuthenticatedMemberToTutor();

		return showProfile("You can now create tutoring offers.");
	}

	
}
