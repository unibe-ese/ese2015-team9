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
public class InfoController {

    @RequestMapping(value = "/info")
    public ModelAndView info() {
    	ModelAndView model = new ModelAndView("info");
        return model;
    }
    
    @RequestMapping(value = "/faq")
    public ModelAndView faq() {
    	ModelAndView model = new ModelAndView("faq");
        return model;
    }    
    
    @RequestMapping(value = "/agb")
    public ModelAndView agb() {
    	ModelAndView model = new ModelAndView("agb");
        return model;
    }    
    
    @RequestMapping(value = "/impressum")
    public ModelAndView impressum() {
    	ModelAndView model = new ModelAndView("impressum");
        return model;
    }    

}


