package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.pojos.SearchFilter;
import team9.tutoragency.controller.pojos.SearchResult;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.University;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;

/**
 * This Service provides methods to obtain a list of courses from the database,
 * that match a given criteria.
 */
@Service
public class SearchService {

	@Autowired
	CourseDao courseDao;
	@Autowired
	MemberDao memberDao;

	public CourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	

	@Transactional
	public List<SearchResult> findCoursesByNameContaining(String substring, SearchFilter filter){
		List<Course> courseMatches;
		if(filter.getUniversities() == null || filter.getUniversities().isEmpty()){
			courseMatches = courseDao.findByNameContainingIgnoreCase(substring);
		} else {
			courseMatches = new ArrayList<Course>();
			for(University university : filter.getUniversities()){
				courseMatches.addAll(courseDao.findByNameContainingAndUniversity(substring, university));
			}
		}
		
		List<Member> memberMatches = memberDao.findByIsTutorTrueAndFeeBetween(filter.getMinFee(), filter.getMaxFee());
		
		List<SearchResult> result = new ArrayList<SearchResult>();
		
		for(Course course: courseMatches){
			List<Member> members = new ArrayList<Member>();
			for(Member member : course.getMembers()){
				if(memberMatches.contains(member));
				members.add(member);
			}
			if(!members.isEmpty())
				result.add(new SearchResult(course, members));
		}
		
		return result;
			
	}
	@Transactional
	public List<SearchResult> findCoursesByNameContaining(String substring) {
		
		return buildResult(courseDao.findByNameContainingIgnoreCase(substring));
	}

	/**
	 * This method would be obsolete if we were able to make the course-member
	 * relation bidirectional.
	 * 
	 * @param courses
	 * @return a list of {@link SearchResult} wrapping the courses and their
	 *         members.
	 */
	private List<SearchResult> buildResult(List<Course> courses) {
		List<SearchResult> results = new ArrayList<SearchResult>();
		for (Course course : courses) {
			results.add(new SearchResult(course, findCourseMembers(course)));
		}

		return results;
	}

	/**
	 * This method would be obsolete if we were able to make the course-member
	 * relation bidirectional.
	 * 
	 * @param course
	 * @return A list of all {@link members} who are related to this course in
	 *         the database.
	 */
	@Transactional
	private List<Member> findCourseMembers(Course course) {
		List<Member> members = new ArrayList<Member>();
		for (Member member : memberDao.findAll()) {
			if (member.getCourseList().contains(course))
				members.add(member);
		}
		return members;
	}

}
