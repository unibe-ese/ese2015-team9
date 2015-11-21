package team9.tutoragency.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;

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
	public ModelAndView showOpenProfile(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("publicProfile");
		Member member = memberService.findById(id);
		model.addObject("member", member);
		return model;
	}
	

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView profile = new ModelAndView("profile");
		Optional<Member> member = memberService.getAuthenticatedMember();
		
		if(!member.isPresent())
			return new ModelAndView("redirect:/login?error=true");
		
		profile.addObject("member", member.get());
		List<Offer> subscriptions = new ArrayList<Offer>();
		subscriptions.addAll(member.get().getSubscriptions());
		profile.addObject("subscriptions", subscriptions);
		return profile;
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
		
		if(!memberService.getAuthenticatedMember().isPresent())
			return new ModelAndView("redirect:/login?error=true");
		
		Member member = memberService.upgradeAuthenticatedMemberToTutor();
		assert(member.isTutor());
		
		return show();
	}

}
