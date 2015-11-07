package team9.tutoragency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PasswordController {

    @RequestMapping(value = "/forgot")
    public ModelAndView forgot() {
    	ModelAndView model = new ModelAndView("forgottenpassword");
        return model;
    }

}


