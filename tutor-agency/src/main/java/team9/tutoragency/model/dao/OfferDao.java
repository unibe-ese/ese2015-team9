package team9.tutoragency.model.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;

public interface OfferDao extends CrudRepository<Offer, Long> {

	public List<Offer> findByTutorAndCourse(Member tutor, Course course);

	public List<Offer> findByCourseIn(Collection<Course> courses);

	public List<Offer> findByTutorInAndCourseInAndGradeGreaterThanEqual(List<Member> tutors,
			List<Course> courses, float parseFloat);
}
