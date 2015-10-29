package team9.tutoragency.controller.pojos;

import javax.validation.constraints.NotNull;

public class AddCourseForm {

	@NotNull
	private String selectedCourse;
	@NotNull
	private String selectedUniversity;
	
	public String getSelectedUniversity() {
		return selectedUniversity;
	}

	public void setSelectedUniversity(String chosenUniversity) {
		this.selectedUniversity = chosenUniversity;
	}

	public String getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(String chosenCourse) {
		this.selectedCourse = chosenCourse;
	}
	
	
	
}
