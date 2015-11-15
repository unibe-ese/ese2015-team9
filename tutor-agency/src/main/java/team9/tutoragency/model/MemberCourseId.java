package team9.tutoragency.model;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class MemberCourseId {

	private Member member;
	private Course course;
	
	@ManyToOne
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@ManyToOne
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

}
