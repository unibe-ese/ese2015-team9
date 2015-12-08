package team9.tutoragency.controller.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.service.SearchService;
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
public class SearchServiceImpl implements SearchService {
	@Autowired
	OfferDao offerDao;

	@Autowired
	CourseDao courseDao;

	@Autowired
	UniversityDao uniDao;

	@Autowired
	MemberDao memberDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see team9.tutoragency.controller.service.SearchService#findOffers(team9.
	 * tutoragency.controller.pojos.SearchForm)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Offer> findOffers(SearchForm form) {

		if (!form.isFiltered()) {
			return findOffersByCourseName(form.getSearchText());
		}

		if (form.getUniversityNames() == null || form.getUniversityNames().isEmpty())
			return new ArrayList<Offer>();
		
		List<Course> courses = new ArrayList<Course>();
		List<Member> members = new ArrayList<Member>();
		List<University> universities = new ArrayList<University>();

		universities = uniDao.findByNameIn(form.getUniversityNames());

		courses = findCoursesByNameAndUniversities(form.getSearchText(), universities);
		members = memberDao.findByFeeBetween(form.getMinFee(), form.getMaxFee());
		return findOffersByTutorsCoursesAndGrades(members, courses, form.getMinGrade());

	}

	@Transactional(readOnly = true)
	private List<Offer> findOffersByCourseName(String courseName) {
		if (courseName == null)
			courseName = "";
		
		List<Course> courses = courseDao.findByNameContainingIgnoreCase(courseName);
		return offerDao.findByCourseIn(courses);
	}

	/**
	 * Invokes either the course dao query method
	 * {@code findByNameContainingIgnoreCase(courseName)} if universities is
	 * empty. Or {@code findByNameContainingIgnoreCaseAndUniversityIn} if
	 * universities are available. Prevents the queries from being invoked with
	 * a null parameter.
	 * 
	 * @param courseName
	 *            If null, it is treated as an empty string.
	 * @param universities
	 *            If null, treated as an empty list.
	 */
	@Transactional(readOnly = true)
	public List<Course> findCoursesByNameAndUniversities(String courseName, List<University> universities) {
		if (courseName == null)
			courseName = "";

		if (universities == null || universities.isEmpty())
			return new ArrayList<Course>();
		else
			return courseDao.findByNameContainingIgnoreCaseAndUniversityIn(courseName, universities);
	}

	/**
	 * Returns a List of offers , either obtained from {@code OfferDao}'s
	 * {@code findByGradeGreaterThanEqual} query method, if members or courses
	 * is null. Or else the
	 * {@code findByTutorInAndCourseInAndGradeGreaterThanEqual} query. Prevents
	 * the queries from being invoked with an empty or null parameters. Asserts
	 * minGrade not null.
	 * 
	 * @param members
	 *            - if null treated as empty list.
	 * @param courses
	 *            - if null treated as empty list.
	 * @param minGrade
	 *            must be not null
	 * 
	 * @throws NumberFormatException
	 *             if the minGrade does not contain a parsable float.
	 */
	@Transactional(readOnly = true)
	public List<Offer> findOffersByTutorsCoursesAndGrades(List<Member> members, List<Course> courses, String minGrade)
			throws NumberFormatException {

		if (minGrade == null || members == null || courses == null || members.isEmpty() || courses.isEmpty())
			return new ArrayList<Offer>();

		float grade = parseGrade(minGrade);

		return offerDao.findByTutorInAndCourseInAndGradeGreaterThanEqual(members, courses, grade);
	}

	private float parseGrade(String gradeAsString) throws NumberFormatException {
		return Float.parseFloat(gradeAsString);
	}
}
