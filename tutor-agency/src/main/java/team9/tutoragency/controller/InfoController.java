package team9.tutoragency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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


