package team9.tutoragency.controller;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;

public class SearchResult {

	private Member member;
	private Course course;

	public SearchResult(Course c, Member m) {
		this.course = c;
		this.member = m;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
