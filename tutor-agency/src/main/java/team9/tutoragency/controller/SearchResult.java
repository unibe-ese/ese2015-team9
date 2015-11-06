package team9.tutoragency.controller;

import java.util.ArrayList;
import java.util.List;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;

public class SearchResult {

	private Course course;
	private List<Member> members;

	public SearchResult() {
		course = new Course();
		members = new ArrayList<Member>();
	}

	public SearchResult(Course course, List<Member> members) {
		this.course = course;
		this.members = members;
	}

	public List<Member> getMembers() {
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
