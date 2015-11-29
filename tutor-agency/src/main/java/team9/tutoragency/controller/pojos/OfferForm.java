package team9.tutoragency.controller.pojos;

import team9.tutoragency.model.Course;

public class OfferForm {

	
	private Long courseId;
	private String grade;
	
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
