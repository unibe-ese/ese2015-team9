package team9.tutoragency.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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

import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.UniversityService;
import team9.tutoragency.controller.service.validation.EditFormValidationService;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Subscription;
import team9.tutoragency.model.University;

/**
 * Handles all interactions of a {@link Member} in order to edit the profile
 * information.
 * 
 * @author laeri
 *
 */
@Controller
@RequestMapping(value="/auth/account")
public class AccountController {

	@Autowired
	EditFormValidationService validator;
	@Autowired
	MemberService memberService;
	@Autowired
	UniversityService uniService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView showProfile() {
		ModelAndView profile = new ModelAndView("profile");
		
		Optional<Member> member = memberService.getAuthenticatedMember();
		
		if(!member.isPresent())
			return new ModelAndView("redirect:/denied");
		
		profile.addObject("member", member.get());
		
		return profile;
	}
	
	/**
	 * Prepares the model for the edit view when a {@link Member} would like to
	 * edit his profile informations. The {@link EditForm} will contain all
	 * values of a {@link Member} to be edited.
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() throws IOException {

		ModelAndView edit = new ModelAndView("edit");

		Optional<Member> authMember = memberService.getAuthenticatedMember();
		
		if(!authMember.isPresent()){
			return new ModelAndView("redirect:../../denied");
		}
		
		//else
		
		EditForm editForm = new EditForm(authMember.get());
		
		List<String> universityNames = uniService.findAllNames();

		edit.addObject("universityChoices", universityNames);
		edit.addObject("editForm", editForm);
		edit.addObject("member", authMember.get());
		return edit;
	}

	/**
	 * Handles saving an {@link EditForm} with the help of the
	 * {@link MemberService} to the database. The {@link EditForm} is validated
	 * by the {@link EditFormValidationService}. If the validation passes the
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
	public ModelAndView saveChange(@Valid EditForm editForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		
		assert(isAccessAuthenticated());

		ModelAndView model;
		validator.validate(editForm, result);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		if (!result.hasErrors()) {

			memberService.saveEditChange(member, editForm);
			model = showProfile();

		} else {

			model = new ModelAndView("edit", "editForm", editForm);
		
			List<String> universityNames = uniService.findAllNames();

			model.addObject("universityChoices", universityNames);
			model.addObject("member", member);
		}
		return model;
	}

	/**
	 * Invariant, that must be true whenever a request method is invoked.
	 * 
	 * @return false, iff the security context has no authentication available
	 *         or the token isn't authenticated.
	 */
	public boolean isAccessAuthenticated() {
		if (SecurityContextHolder.getContext().getAuthentication() == null)
			return false;
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
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
		
		return showProfile();
	}
	
}
