package team9.tutoragency.model.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;

public interface OfferDao extends CrudRepository<Offer, Long> {

	public List<Offer> findByMemberAndCourse(Member member, Course course);

	public List<Offer> findByMember(Member tutor);

	public List<Offer> findByCourseIn(Collection<Course> courses);

	public List<Offer> findByMemberInAndCourseIn(Collection<Member> memberMatches, Collection<Course> courseMatches);

	public Collection<? extends Offer> findByCourse(Course course);

	public List<Offer> findByGradeGreaterThanEqual(float minGrade);

	public List<Offer> findByMemberInAndCourseInAndGradeGreaterThanEqual(List<Member> members,
			List<Course> courses, float parseFloat);
}
