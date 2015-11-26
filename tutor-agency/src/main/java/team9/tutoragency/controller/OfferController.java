package team9.tutoragency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import team9.tutoragency.controller.service.OfferService;
import team9.tutoragency.controller.service.SubscriptionService;

@Controller
public class OfferController {

	@Autowired OfferService offerService;
	@Autowired SubscriptionService subscriptionService;
	
	@RequestMapping(value = "/subscribe/{offerId}", method = RequestMethod.GET)
	public String subscribe(@PathVariable(value = "offerId") Long offerId) {
		offerService.subscribeAuthMemberToOffer(offerId);
		return "redirect:/profile";
	}
	
	@RequestMapping(value = "/accept/{subscriptionId}", method = RequestMethod.GET)
	public String acceptSubscription(@PathVariable(value = "subscriptionId") Long id){
		subscriptionService.acceptSubscriptionIfFromAuthMember(id);
		return "redirect:/profile";
	}
}
