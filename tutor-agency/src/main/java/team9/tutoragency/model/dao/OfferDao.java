package team9.tutoragency.model.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.TutoringOffer;

public interface OfferDao extends CrudRepository<TutoringOffer,Long>{

	public List<TutoringOffer> findByMemberAndCourse(Member member, Course course);
}
