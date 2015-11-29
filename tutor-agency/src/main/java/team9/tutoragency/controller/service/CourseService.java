package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;


import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.OfferDao;
import team9.tutoragency.model.dao.UniversityDao;

@Service
public class CourseService {

	@Autowired
	CourseDao courseDao;


	@Transactional(readOnly=true)
	public List<Course> findByUniversity(University university) {
		return courseDao.findByUniversity(university);
	}


	/**
	 * Invokes the course dao query method findByNameContainingIgnoreCase(courseName).
	 * Prevents the query from being invoked with a null parameter.
	 * @param searchText - if null, it is treated as empty string.
	 */
	@Transactional(readOnly = true)
	public List<Course> findByNameContaining(String courseName) {
		if(courseName == null)
			courseName = "";
		
		return courseDao.findByNameContainingIgnoreCase(courseName);
	}


	/**
	 * Invokes either the course dao query method {@code findByNameContainingIgnoreCase(courseName)} if universities is empty. Or 
	 * {@code findByNameContainingIgnoreCaseAndUniversityIn} if universities are available.
	 * Prevents the queries from being invoked with a null parameter.
	 * @param courseName If null, it is treated as an empty string.
	 * @param universities If null, treated as an empty list.
	 */
	@Transactional(readOnly = true)
	public List<Course> findByNameAndUniversities(String courseName, List<University> universities) {
		if(courseName == null)
			courseName = "";
		
		if(universities == null || universities.isEmpty())
			return courseDao.findByNameContainingIgnoreCase(courseName);
		else
			return courseDao.findByNameContainingIgnoreCaseAndUniversityIn(courseName, universities);
	}


	public University findUniversityForCourse(long selectedCourse) {
		return courseDao.findOne(selectedCourse).getUniversity();
	}


}
