package team9.tutoragency.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import team9.tutoragency.controller.pojos.EditForm;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.MemberDao;

@Controller
public class ProfileController {

	@Autowired
	MemberDao memberDao;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView register(HttpServletResponse response) throws IOException {
		ModelAndView profile = new ModelAndView("profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		profile.addObject("member", member);
		return profile;
	}
	
	@RequestMapping(value = "/becomeTutor", method = RequestMethod.POST)
	public ModelAndView becomeTutor(HttpServletResponse response) throws IOException {
		ModelAndView profile = new ModelAndView("profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		member.setIsTutor(true);
		memberDao.save(member);
		profile.addObject("member", member);
		return profile;
	}
	

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(HttpServletResponse response) throws IOException {
		ModelAndView profile = new ModelAndView("edit");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		EditForm editForm = new EditForm();
		editForm.setFirstName(member.getFirstName());
		editForm.setLastName(member.getLastName());
		editForm.setUsername(member.getUsername());
		editForm.setEmail(member.getEmail());
		editForm.setUsername(member.getUsername());
		
		profile.addObject("editForm", editForm);
		return profile;
	}
}
