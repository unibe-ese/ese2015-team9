package team9.tutoragency.controller;

import org.springframework.beans.factory.annotation.Autowired;

import team9.tutoragency.controller.service.CourseService;
import team9.tutoragency.controller.service.OfferService;
import team9.tutoragency.controller.service.UniversityAccessService;

public class addOfferController {

	@Autowired UniversityAccessService uniService;
	@Autowired CourseService courseService;
	@Autowired OfferService offerService;
	
	
	
}
