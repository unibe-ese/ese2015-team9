package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	/**
	 * Finds all courses with the given University as field.
	 * <p>
	 * <b>Asserts that:<b>
	 * <li>University is not null</li>
	 * </p>
	 */
	@Transactional(readOnly=true)
	public List<Course> findByUniversity(University university) {
		if (university==null) throw new AssertionError("University is NULL!");

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

	/**
	 * Asserts courseId not null.
	 * @param courseId must be not null
	 * @return empty Optional if course with given id does not exist.
	 */
	public Optional<Course> findOne(Long courseId) {
		if(courseId==null) throw new AssertionError("Course Id is Null");
		return Optional.ofNullable(courseDao.findOne(courseId));
	}
}
