package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.UniversityDao;

@Service
public class BasicDataServiceImpl implements BasicDataService{
	@Autowired
	CourseDao courseDao;
	
	@Autowired
	UniversityDao uniDao;
	
	/**
	 * Finds all courses with the given University as field.
	 * <p>
	 * <b>Asserts that:<b>
	 * <li>University is not null</li>
	 * </p>
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Course> findCourseByUniversity(University university) {
		if (university==null) throw new AssertionError("University is NULL!");
		return courseDao.findByUniversity(university);
	}

	/**
	 * Asserts courseId not null.
	 * @param courseId must be not null
	 * @return empty Optional if course with given id does not exist.
	 */
	@Override
	public Optional<Course> findCourse(Long courseId) {
		if(courseId==null) throw new AssertionError("Course Id is Null");
		return Optional.ofNullable(courseDao.findOne(courseId));
	}

	@Override
	@Transactional
	public List<University> findAllUniversites() {
		return uniDao.findAll();
	}

	/**
	 * Returns an empty Optional if no university with this id is found, or an Optional wrapping the University with the given id.
	 * <p>
	 * <b>Asserts that:<b>
	 * <li>uniId is not null</li>
	 * <p>
	 */
	@Override
	public Optional<University> findUniversity(Long uniId) {
		if (uniId==null) throw new AssertionError("uniId is NULL");
		return Optional.ofNullable(uniDao.findOne(uniId));
	}

	/**
	 * Returns the result of {@code uniDao}'s {@code findByNameIn} method. Prevents the query from being
	 * invoked with an empty list. 
	 * @param names If null, an empty List is returned.
	 */
	@Override
	@Transactional(readOnly=true)
	public List<University> findUniversitiesByNames(List<String> names) {
		if(names == null || names.isEmpty())
			return new ArrayList<University>();
		
		return uniDao.findByNameIn(names);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> findAllUniversityNames() {
		List<String> names = new ArrayList<String>();
		for(University uni : uniDao.findAll()){
			names.add(uni.getName());
		}
		return names;
	}		
	

}
