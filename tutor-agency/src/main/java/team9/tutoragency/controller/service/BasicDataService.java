package team9.tutoragency.controller.service;

import java.util.List;
import java.util.Optional;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.University;

/**
 * An implementation of this interface gives read only access to the courses and
 * universities (the basic data, which cannot be changed trough the UI). They
 * are used e.g. as selection data when creating an offer.
 * 
 * @author bruno
 *
 */
public interface BasicDataService {

	List<University> findAllUniversites();

	Optional<University> findUniversity(Long uniId);

	List<University> findUniversitiesByNames(List<String> names);

	List<String> findAllUniversityNames();

	List<Course> findCourseByUniversity(University university);

	Optional<Course> findCourse(Long courseId);
}
