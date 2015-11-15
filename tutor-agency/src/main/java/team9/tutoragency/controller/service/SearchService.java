package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.pojos.SearchForm;
import team9.tutoragency.controller.pojos.SearchResult;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;


/**
 * This Service provides methods to obtain a list of courses from the database,
 * that match a given criteria.
 */
@Service
public class SearchService {

	@Autowired
	CourseDao courseDao;
	
	@Autowired
	UniversityAccessService uniService;
	
	public List<SearchResult> findCoursesByNameContaining(SearchForm form){
		if(!form.isFiltered())
			return findCoursesByNameContaining(form.getSearchText());
			
		//else
			
		List<University> universityMatches = uniService.findByNames(form.getUniversityNames());	
		
		List<Course> courseMatches = new ArrayList<Course>();
		for(University university : universityMatches){
				courseMatches.addAll(findCourses(form.getSearchText(), university));
		}
		
		List<SearchResult> results = new ArrayList<SearchResult>();	
		for(Course course: courseMatches){
			List<Member> members = new ArrayList<Member>();
			
			for(Member member : course.getMembers()){
				
				double fee = member.getFee();
				if(fee >= form.getMinFee() && fee <= form.getMaxFee())
					members.add(member);
			}
			if(!members.isEmpty())
				results.add(new SearchResult(course, members));
		}
		
		return results;
			
	}
	
	/**
	 * This method returns {@link CourseDao#findByNameContainingAndUniversity(courseName, university)}.	
	 * (To isolate the Transaction from the form evaluation.)
	 * @param courseName
	 * @param university
	 * @return
	 */
	@Transactional(readOnly=true)
	private List<Course> findCourses(String courseName, University university) {
		return courseDao.findByNameContainingAndUniversity(courseName, university);
	}
	@Transactional
	public List<SearchResult> findCoursesByNameContaining(String substring) {
		return buildResult(courseDao.findByNameContainingIgnoreCase(substring));
	}

	/**
	 * 
	 * @param courses
	 * @return a list of {@link SearchResult} wrapping the courses and their
	 *         members.
	 */
	private List<SearchResult> buildResult(List<Course> courses) {
		List<SearchResult> results = new ArrayList<SearchResult>();
		for (Course course : courses) {
			results.add(new SearchResult(course, course.getMembers()));
		}

		return results;
	}

	

}
