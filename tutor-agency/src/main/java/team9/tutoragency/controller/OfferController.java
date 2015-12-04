package team9.tutoragency.controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import team9.tutoragency.controller.service.AgencyService;
import team9.tutoragency.controller.service.MemberService;
import team9.tutoragency.controller.service.OfferService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;

@Controller
@RequestMapping(value="/auth/offer/{offerId}")
public class OfferController {
	@Autowired MemberService memberService;
	@Autowired AgencyService agencyService;
	@Autowired OfferService offerService;
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public String subscribe(@PathVariable(value = "offerId") Long offerId) {
		agencyService.subscribeMemberToOffer(memberService.getAuthenticatedMember().get().getId(), offerId);
		return "redirect:/auth/account";
	}
	
	@RequestMapping(value = "/accept/{subscriptionId}/", method = RequestMethod.GET)
	public String acceptSubscription(@PathVariable(value = "offerId") Long offerId, @PathVariable(value = "subscriptionId") Long subscriptionId){
		Optional<Offer> offer = agencyService.findOfferById(offerId);
		Optional<Member> member = memberService.getAuthenticatedMember();
		if(offer.isPresent() && member.isPresent() && offer.get().getTutor().equals(member.get()))
            agencyService.acceptSubscription(subscriptionId);
		return "redirect:/auth/account#offers";
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
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteCourse(@PathVariable("offerId") Long id) throws IOException {
		Optional<Offer> offer = agencyService.findOfferById(id);
		Optional<Member> member = memberService.getAuthenticatedMember();

		if (offer.isPresent() && Objects.equals(member.get().getId(), offer.get().getTutor().getId()))
			agencyService.removeOffer(id);

		return "redirect:/auth/account";
	}
}
