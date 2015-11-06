package team9.tutoragency.controller.pojos;

public class SearchForm {
import javax.validation.constraints.NotNull;

public class searchForm {

	
	private String selectedCourse;
	
	private String searchText;
	private String selectedUniversity;
	
	public String getSelectedUniversity() {
		return selectedUniversity;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getSelectedCourse() {
		return selectedCourse;
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
