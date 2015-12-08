package team9.tutoragency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class handles mapping requests, that are used by Spring Security. 
 * @see src/main/webapp/WEB-INF/config/springSecurity.xml  
 * @author bruno
 */
@Controller
public class LoginController {

	public static final String loginErrorMessage = "You have entered an invalid username or password!";
	public static final String accessDeniedMessage = "Access-Denied! Log in to see this page.";
	
	/**
	 * This url is used as target, when authentication-failure occurs for a login request.
	 * @return ModelAndView with loginpage.jsp as view, and {@code loginErrorMessage} added as object.
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
	 * This url is used as target, when spring-security intercepts an url.
	 * @return ModelAndView with loginpage.jsp as view, and {@code accessDeniedMessage} added as object.
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView getDeniedPage() {
		ModelAndView model = new ModelAndView("loginpage");
		model.addObject("message", accessDeniedMessage);
		return model;
	}
}
