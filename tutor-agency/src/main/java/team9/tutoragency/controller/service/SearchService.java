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
	CourseService courseService;

	@Autowired
	UniversityService uniService;

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
			courses = courseService.findByNameContaining(form.getSearchText());		
			return offerService.findByCourses(courses);
		}
			
		//else

		if (form.getUniversityNames() == null || form.getUniversityNames().isEmpty()) 
			universities = uniService.findAll();
		else
			universities = uniService.findByNames(form.getUniversityNames());

		courses = courseService.findByNameAndUniversities(form.getSearchText(), universities);
		members = memberService.findByFee(form.getMinFee(), form.getMaxFee() );
		return offerService.findByTutorsCoursesAndGrades(members, courses,form.getMinGrade());
		
	}

}
