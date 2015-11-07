package team9.tutoragency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team9.tutoragency.controller.pojos.SignupForm;

@Controller
public class IndexController {


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("signupForm", new SignupForm());
		return model;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index1() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("signupForm", new SignupForm());
		return model;
	}

	@RequestMapping(value = "/security-error", method = RequestMethod.GET)
	public String securityError(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
		return "redirect:/";
	}

}
