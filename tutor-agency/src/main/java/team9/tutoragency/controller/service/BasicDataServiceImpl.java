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

/**
 * Implementation of {@code BasicDataService}.
 * @see {@linkplain BasicDataService}.
 * @author bruno
 *
 */
@Service
public class BasicDataServiceImpl implements BasicDataService{
	
	@Autowired
	CourseDao courseDao;
	
	@Autowired
	UniversityDao uniDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Course> findCoursesByUniversity (University university) throws AssertionError{
		if (university==null) throw new AssertionError("University is NULL!");
		return courseDao.findByUniversity(university);
	}

	@Override
	public Optional<Course> findCourse(Long courseId) throws AssertionError{
		if(courseId==null) throw new AssertionError("Course Id is Null");
		return Optional.ofNullable(courseDao.findOne(courseId));
	}

	@Override
	@Transactional
	public List<University> findAllUniversites(){
		return uniDao.findAll();
	}

	@Override
	public Optional<University> findUniversity(Long uniId) throws AssertionError{
		if (uniId==null) throw new AssertionError("uniId is NULL");
		return Optional.ofNullable(uniDao.findOne(uniId));
	}

	@Override
	@Transactional(readOnly=true)
	public List<University> findUniversitiesByNames(List<String> names){
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
