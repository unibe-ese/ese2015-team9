package team9.tutoragency.controller.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains only a String representing a search criteria. 
 *
 */
public class QuickSearchForm {

	private String searchText;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((searchText == null) ? 0 : searchText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuickSearchForm other = (QuickSearchForm) obj;
		if (searchText == null) {
			if (other.searchText != null)
				return false;
		} else if (!searchText.equals(other.searchText))
			return false;
		return true;
	}

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
