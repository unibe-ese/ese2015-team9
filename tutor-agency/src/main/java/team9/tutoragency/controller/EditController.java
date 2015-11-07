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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.controller.service.EditFormValidationService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.UniversityAccessService;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;

@Controller
public class EditController {

	@Autowired
	EditFormValidationService validator;
	@Autowired
	MemberService memberService;
	@Autowired
	UniversityAccessService uniService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletResponse response) throws IOException {
		ModelAndView edit = new ModelAndView("edit");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		EditForm editForm = new EditForm();
		editForm.setFirstName(member.getFirstName());
		editForm.setLastName(member.getLastName());
		editForm.setUsername(member.getUsername());
		editForm.setEmail(member.getEmail());
		editForm.setUsername(member.getUsername());
		if (member.getFee() != null) {
			editForm.setFee(member.getFee().toString());
		} else {
			editForm.setFee("0");
		}
		edit.addObject("editForm", editForm);
		List<University> universities = uniService.findAll();
		List<String> universityNames = new ArrayList<String>();
		for (University uni : universities) {
			universityNames.add(uni.getName());
		}

		edit.addObject("universityChoices", universityNames);
		edit.addObject("member", member);
		return edit;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveChange(@Valid EditForm editForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		ModelAndView model;
		validator.validate(editForm, result);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		if (!result.hasErrors()) {

			memberService.saveEditChange(member, editForm);
			model = new ModelAndView("profile");
			model.addObject("member", member);
			model.addObject("unis", member.getUniversityList());

		} else {

			model = new ModelAndView("edit");
			model.addObject("editForm", editForm);
			List<University> universities = uniService.findAll();
			List<String> universityNames = new ArrayList<String>();
			for (University uni : universities) {
				universityNames.add(uni.getName());
			}

			model.addObject("universityChoices", universityNames);
			model.addObject("member", member);

		}
		return model;
	}
}
