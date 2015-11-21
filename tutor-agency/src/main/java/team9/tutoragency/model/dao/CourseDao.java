package team9.tutoragency.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.University;

public interface CourseDao extends CrudRepository<Course,Long> {
	
	public List<Course> findByUniversity(University university);
	public List<Course> findById(Long id);
	public List<Course> findByName(String name);
	public List<Course> findByNameContainingIgnoreCase(String name);
	public List<Course> findByNameContainingAndUniversity(String name, University university);
	public List<Course> findByNameContainingAndUniversityIn(String searchText, List<University> universities);
	
}