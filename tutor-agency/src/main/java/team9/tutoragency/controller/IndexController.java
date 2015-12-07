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
		return model;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index1() {
		ModelAndView model = new ModelAndView("index");
		return model;
	}

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
