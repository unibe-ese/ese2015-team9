package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.Offer;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;
import team9.tutoragency.model.dao.OfferDao;
import team9.tutoragency.model.dao.UniversityDao;

/**
 * This Service provides a method to obtain a list of offers from the database,
 * which mach critera, specified in a {@link SearchForm}.
 */
@Service
public class SearchService {
	@Autowired
	OfferService offerService;

	@Autowired
	CourseDao courseDao;

	@Autowired
	UniversityService uniService;

	@Autowired
	MemberDao memberDao;

	@Autowired
	OfferDao offerDao;

	@Autowired
	UniversityDao uniDao;

	/**
	 * Returns a List of {@link TutoringOffers} matching the specified criteria in the search form. 
	 * @param form
	 * @return
	 */
	@Transactional
	public List<Offer> findOffers(SearchForm form) {
		if (form.getSearchText() == null)
			form.setSearchText("");

		List<Offer> offers = new ArrayList<Offer>();
		System.err.println(form.toString());
		List<Course> courses = new ArrayList<Course>();
		List<Member> members = new ArrayList<Member>();
		List<University> universities = new ArrayList<University>();

		if (form.isFiltered()) {

			if (form.getUniversityNames() == null || form.getUniversityNames().isEmpty())
				universities = uniService.findAll();
			else
				universities = uniDao.findByNameIn(form.getUniversityNames());

			System.err.println(universities.toString());

			courses = courseDao.findByNameContainingAndUniversityIn(form.getSearchText(), universities);
			System.err.println(courses);

			members = memberDao.findByFeeBetween(new Double(form.getMinFee() ), form.getMaxFee() );
			System.out.println(members);

			if (courses == null || members == null || courses.isEmpty() || members.isEmpty())
				return offers;
			else {
				return offerDao.findByTutorInAndCourseInAndGradeGreaterThanEqual(members, courses,
						Float.parseFloat(form.getMinGrade()));
			}
		} else
			return offerDao.findByCourseIn(courseDao.findByNameContainingIgnoreCase(form.getSearchText()));
	}

}
