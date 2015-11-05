package team9.tutoragency.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;
import team9.tutoragency.model.dao.CourseDao;
import team9.tutoragency.model.dao.MemberDao;

/**
 * This Service provides methods to optain a list of courses from the Database, which match a given criteria.
 *
 */
@Service
public class SearchService {

	@Autowired CourseDao courseDao;
	@Autowired MemberDao memberDao;
	public List<SearchResult> findCoursesByNameContaining(String substring){
		return buildResult(courseDao.findByNameContaining(substring));
	}

	private List<SearchResult> buildResult(List<Course> courses) {
		List<SearchResult> results = new ArrayList<SearchResult>();
		for(Course course: courses){
			List<Member> members = new ArrayList<Member>();
			for(Member member :memberDao.findAll()){
				if(member.getCourseList().contains(course))
					members.add(member);
			}
			results.add(new SearchResult(course, members));
		}
				
		return results;
	}

	public class SearchResult{
		private Course course;
		private List<Member> members;
		public SearchResult(){
			course = new Course();
			members= new ArrayList<Member>();
		}
		public SearchResult(Course course, List<Member> members) {
			this.course = course;
			this.members = members;
		}
		public List<Member> getMembers(){
			return members;
		}
		public Course getCourse() {
			return course;
		}
		public void setCourse(Course course) {
			this.course = course;
		}
		public void setMembers(List<Member> members) {
			this.members = members;
		}
		
	}
}
