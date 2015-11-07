package team9.tutoragency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	public static final String loginErrorMessage = "You have entered an invalid username or password!";
	public static final String accessErrorMessage = "Access-Denied! Log in to see this page.";
	
	/**
	 * This method handels the request for the loginpage with or without a login-error.
	 * For configuration of the login-fail, see the springSecurity.xml file.
	 * @param error if true, the error-message for the login page is set to {@value #loginErrorMessage}, else to an empty String.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) boolean error) {
		ModelAndView model = new ModelAndView("loginpage");
		if (error) {
			model.addObject("message", loginErrorMessage );
		} else {
			model.addObject("message", " ");
		}
		return model;
	}

	/**
	 * This method is called whenever a visiter attempts to access an user only page.
	 * This is definened in the springSecurity.xml file.
	 * @return ModelAndView with loginpage.jsp as view, and "access denied"-message added as object.
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView getDeniedPage() {
		ModelAndView model = new ModelAndView("loginpage");
		model.addObject("message", accessErrorMessage);
		return model;
	}
}
