package team9.tutoragency.controller;

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
import team9.tutoragency.controller.service.SampleService;

@Controller
public class SearchController {

    @RequestMapping(value = "/ssearch")
    public ModelAndView ssearch() {
    	ModelAndView model = new ModelAndView("simpleSearch");
        return model;
    }
    
    @RequestMapping(value = "/asearch")
    public ModelAndView asearch() {
    	ModelAndView model = new ModelAndView("asearch");
        return model;
    }    

}


