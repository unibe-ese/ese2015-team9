package team9.tutoragency.controller.service;

import java.util.List;
import java.util.Optional;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.University;

/**
 * An implementation of this interface gives read only access to the courses and
 * universities (the basic data, which cannot be changed trough the UI). They
 * are used e.g. as selection data when creating an offer.
 * @see {@link University}, {@link Course}
 * 
 * @author bruno
 *
 */
public interface BasicDataService {

	List<University> findAllUniversites();

	/** 
	 * @param uniId
	 *            mustn't be null.
	 * @return An empty {@code Optional} when no University with this was found.
	 *         Else, an Optional wrapping the matching {@code University}.
	 * @throws AssertionError
	 *             thrown when id is null.
	 */
	Optional<University> findUniversity(Long uniId) throws AssertionError;

	/**
	 * A list containing each {@code University} with a name in {@code names}.
	 * @param names of the Universities. If null, an empty List is returned.
	 */
	List<University> findUniversitiesByNames(List<String> names);

	List<String> findAllUniversityNames();

	/**
	 * 
	 * @param university mustn't be null
	 * @return A list containing all courses with given {@code University}.
	 * @throws AssertionError thrown when {@code id} is null.
	 */
	List<Course> findCoursesByUniversity(University university) throws AssertionError;

	/**
	 * 
	 * @param courseId
	 *            mustn't be null
	 * @return An empty {@code Optional} when no {@code Course} with this
	 *         {@code id} was found. Else, an Optional wrapping the matching
	 *         {@code Course}.
	 * @throws AssertionError
	 *             thrown when id is null.
	 */
	Optional<Course> findCourse(Long courseId) throws AssertionError;
}
