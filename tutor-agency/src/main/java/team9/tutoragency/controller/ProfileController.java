package team9.tutoragency.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.model.Member;

/**
 * The {@link ProfileController} handles the interactions of a user with his
 * profile if he/she either wants to show their profile or upgrade to a tutor.
 * 
 * @author laeri
 *
 */
@Controller
public class ProfileController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/profileId={id}", method = RequestMethod.GET)
	public ModelAndView showPublicProfile(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("publicProfile");
		
		Optional<Member> member = memberService.findById(id);
		
		if(member.isPresent())
			model.addObject("member", member);
		else
			model.setViewName("redirect:/");
		
		return model;
	}
}
