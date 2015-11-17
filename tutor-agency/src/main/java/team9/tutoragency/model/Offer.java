package team9.tutoragency.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import team9.tutoragency.controller.pojos.AddCourseForm;

@Entity
public class Offer implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	private float grade;

	public Offer(Member member, Course course, float grade) {
		this.member = member;
		this.course = course;
		this.grade = grade;
	}
	public Offer(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

}
