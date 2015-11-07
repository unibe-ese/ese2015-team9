package team9.tutoragency.controller.pojos;

import javax.validation.constraints.NotNull;

import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;

/**
 * The AddCourseForm is used to store any data which is needed to add a
 * {@link Course} to a {@link Member} in the addCourse view. After a
 * {@link Member} has added a {@link Course} for which he would offer tutoring
 * lessons, all the Courses are displayed in the showCourses view.
 * 
 * @author laeri
 *
 */
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
