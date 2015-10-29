package team9.tutoragency.controller.pojos;

import javax.validation.constraints.NotNull;

import team9.tutoragency.model.Course;

public class AddCourseForm {

	@NotNull
	private Course chosenCourse;

	public Course getChosenCourse() {
		return chosenCourse;
	}

	public void setChosenCourse(Course chosenCourse) {
		this.chosenCourse = chosenCourse;
	}
	
	
	
}
