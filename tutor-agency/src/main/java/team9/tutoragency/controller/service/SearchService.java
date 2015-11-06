package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.controller.SearchResult;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
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
	public List<SearchResult> findCoursesByNameContaining(String substring) {
		return buildResult(courseDao.findByNameContaining(substring));
	}

	/**
	 * This method would be obsolete if we were able to make the course-member
	 * relation bidirectional.
	 * 
	 * @param courses
	 * @return a list of {@link SearchResult} wrapping the courses and their
	 *         members.
	 */
	@Transactional
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
