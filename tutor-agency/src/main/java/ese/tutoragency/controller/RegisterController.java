package ese.tutoragency.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ese.tutoragency.controller.pojos.SignupForm;
import ese.tutoragency.controller.service.SampleService;

@Controller
public class RegisterController {

	@Autowired
   SampleService sampleService;
	
	@RequestMapping(value = "/register" , method = RequestMethod.GET)
	public ModelAndView register(HttpServletResponse response) throws IOException {
		ModelAndView register = new ModelAndView("register");
		register.addObject("signupForm", new SignupForm());
		return register;
	}
	
	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public ModelAndView createUser(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
		System.out.println("/create");
		ModelAndView model;    
            //	sampleService.saveFrom(signupForm);
            	model = new ModelAndView("home");
    	return model;
	}
}
