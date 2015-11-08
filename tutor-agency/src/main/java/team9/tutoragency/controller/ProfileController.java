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
	  public ModelAndView showProfile(@PathVariable("id") Long id) {
		  ModelAndView model = new ModelAndView("profile");
		  Member member = memberService.findById(id);
		  model.addObject("member", member);
		  model.addObject("memberAtHome", isLoggedIn(member));
		  return model;
	  }
	
	private boolean isLoggedIn(Member member) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 if(member.equals(authentication.getPrincipal()))
			 return true;
		 else
			 return false;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView show(HttpServletResponse response) throws IOException {
		ModelAndView profile = new ModelAndView("profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		profile.addObject("member", member);
		profile.addObject("unis", member.getUniversityList());
		return profile;
	}

	
	@RequestMapping(value = "/becomeTutor", method = RequestMethod.POST)
	public ModelAndView becomeTutor(HttpServletResponse response) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		memberService.upgradeToTutor(member);
		return show(response);
	}
	
}
