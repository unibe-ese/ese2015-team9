package team9.tutoragency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginLogoutController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value = "error", required = false) boolean error,
			@RequestParam(value = "denied", required = false) boolean denied, ModelMap model) {
		if (error) {
			model.put("error", "You have entered an invalid username or password!");
		} else {
			model.put("error", " ");
		}
		if (denied) {
			model.put("denied", "Access-Denied! Log in to see this page.");
		} else {
			model.put("denied", " ");
		}
		return "loginpage";
	}

	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a
	 * regular user tries to access an admin only page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView getDeniedPage() {
		ModelAndView model = new ModelAndView("access-denied");
//		model.addObject("denied", "Access-Denied! Log in to see this page.");

		return model;
	}
}
