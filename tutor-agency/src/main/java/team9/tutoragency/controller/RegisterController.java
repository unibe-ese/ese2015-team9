package team9.tutoragency.controller;

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

import team9.tutoragency.controller.exceptions.InvalidUserException;
import team9.tutoragency.controller.pojos.SignupForm;
import team9.tutoragency.controller.service.SignupFromSaveService;
import team9.tutoragency.controller.service.validation.SignupFormValidationService;
import team9.tutoragency.model.Member;

/**
 * The {@link RegisterController} is responsible for all interactions when a
 * user wants to register as a {@link Member} of the Tutoring Agency.
 * 
 * @author laeri
 *
 */
@Controller
public class RegisterController {

	@Autowired
	SignupFromSaveService saveService;

	@Autowired
	SignupFormValidationService validator;

	/**
	 * Creates the model for the register view. A {@link SignupForm} will
	 * contain all informations a user needs to edit in order to register
	 * successfully.
	 * 
	 * @return model for the register view
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(HttpServletResponse response) throws IOException {
		ModelAndView register = new ModelAndView("register");
		register.addObject("signupForm", new SignupForm());
		return register;
	}

	/**
	 * Tries to create a {@link Member} based on the information provided in the
	 * {@link SignupForm}. If the result doesn't have any errors the
	 * {@link SignupFromSaveService} tries to save the member persistently to
	 * the database. If there are any errors, the user isn't redirected but a
	 * number of errors are displayed.
	 * 
	 * @param signupForm
	 *            model of the form which was edited in the register view
	 * @return model registerSuccess if successful otherwise the register form
	 *         again to edit changes
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createUser(@Valid SignupForm signupForm, BindingResult result,
			RedirectAttributes redirectAttributes) throws IOException {
		ModelAndView model;
		validator.validate(signupForm, result);
		if (!result.hasErrors()) {
			try {
				saveService.saveFrom(signupForm);
				model = new ModelAndView("registerSuccess");
			} catch (InvalidUserException e) {
				model = new ModelAndView("register", "signupForm", signupForm);
			}
		} else {

			model = new ModelAndView("register", "signupForm", signupForm);
		}
		return model;
	}
}
