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
	OfferDao offerDao;

	@Autowired
	CourseDao courseDao;

	@Autowired
	UniversityDao uniDao;

	@Autowired
	MemberService memberService;
	/**
	 * Returns a List of {@link TutoringOffers} matching the specified criteria in the search form. 
	 * @param form - must be not null
	 * @return
	 */
	public List<Offer> findOffers(SearchForm form) {

		List<Course> courses = new ArrayList<Course>();
		List<Member> members = new ArrayList<Member>();
		List<University> universities = new ArrayList<University>();
		
		if(!form.isFiltered()){
			courses = findCoursesByNameContaining(form.getSearchText());		
			List<Offer> offers = new ArrayList<Offer>();
			for(Course course: courses){
				offers.addAll(course.getOffers());
			}
			return offers;
		}
			
		//else

		if (form.getUniversityNames() == null || form.getUniversityNames().isEmpty()) 
			universities = uniDao.findAll();
		else
			universities = uniDao.findByNameIn(form.getUniversityNames());

		courses = findCoursesByNameAndUniversities(form.getSearchText(), universities);
		members = memberService.findByFee(form.getMinFee(), form.getMaxFee() );
		return findOffersByTutorsCoursesAndGrades(members, courses,form.getMinGrade());
		
	}
	
	/**
	 * Invokes the course dao query method findByNameContainingIgnoreCase(courseName).
	 * Prevents the query from being invoked with a null parameter.
	 * @param searchText - if null, it is treated as empty string.
	 */
	@Transactional(readOnly = true)
	public List<Course> findCoursesByNameContaining(String courseName) {
		if(courseName == null)
			courseName = "";
		
		return courseDao.findByNameContainingIgnoreCase(courseName);
	}

	/**
	 * Invokes either the course dao query method {@code findByNameContainingIgnoreCase(courseName)} if universities is empty. Or 
	 * {@code findByNameContainingIgnoreCaseAndUniversityIn} if universities are available.
	 * Prevents the queries from being invoked with a null parameter.
	 * @param courseName If null, it is treated as an empty string.
	 * @param universities If null, treated as an empty list.
	 */
	@Transactional(readOnly = true)
	public List<Course> findCoursesByNameAndUniversities(String courseName, List<University> universities) {
		if(courseName == null)
			courseName = "";
		
		if(universities == null || universities.isEmpty())
			return courseDao.findByNameContainingIgnoreCase(courseName);
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
		assert minGrade != null;

		float grade = parseGrade(minGrade);

		if (members == null || courses == null || members.isEmpty() || courses.isEmpty())
			return offerDao.findByGradeGreaterThanEqual(grade);
		else
			return offerDao.findByTutorInAndCourseInAndGradeGreaterThanEqual(members, courses, grade);
	}

	private float parseGrade(String gradeAsString) throws NumberFormatException {
		return Float.parseFloat(gradeAsString);
	}
}
