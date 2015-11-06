package team9.tutoragency.controller.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains only a String representing a search criteria. 
 *
 */
public class QuickSearchForm {

	private String searchText;
	
	public QuickSearchForm(){
		searchText = "";
	}
	
	public QuickSearchForm(String searchText){
		this.searchText = searchText;
	}
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
}
