package team9.tutoragency.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import team9.tutoragency.controller.pojos.AddCourseForm;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.UniversityDao;

@Controller
public class CourseController {

	@Autowired
	UniversityDao uniDao;
	@Autowired
	CourseDao courseDao;
	
	@RequestMapping(value = "/addCourse", method = RequestMethod.GET)
	public ModelAndView addCourse(HttpServletResponse response) throws IOException {
		ModelAndView addCourse = new ModelAndView("addCourse");
		addCourse.addObject("addCourseForm", new AddCourseForm());
		addCourse.addObject("universities",uniDao.findAll());
		addCourse.addObject("courses",courseDao.findAll());
		
		return addCourse;
	}
}
