package team9.tutoragency.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.model.Member;

@Controller
public class ProfileController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/profileId={id}", method = RequestMethod.GET)
	  public ModelAndView showOpenProfile(@PathVariable("id") Long id) {
		  ModelAndView model = new ModelAndView("openprofile");
		  Member member = memberService.findById(id);
		  model.addObject("member", member);
//		  model.addObject("memberAtHome", isLoggedIn(member));
		  return model;
	  }
//	/**
//	 * This method returns true iff {@link Member#getUsername()} is equal to the current authentications principal (name).
//	 * @param member
//	 * @return
//	 */
//	public boolean isLoggedIn(Member member) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		 if(member.getUsername().equals(authentication.getName()))
//			 return true;
//		 else
//			 return false;
//	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView profile = new ModelAndView("profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		profile.addObject("member", member);
		return profile;
	}

	/**
	 * This method sets the to the current authentications principal equivalent users isTutor flag to true.
	 * @return
	 */
	@RequestMapping(value = "/becomeTutor", method = RequestMethod.POST)
	public ModelAndView becomeTutor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		memberService.upgradeToTutor(member);
		return show();
	}
	
}
