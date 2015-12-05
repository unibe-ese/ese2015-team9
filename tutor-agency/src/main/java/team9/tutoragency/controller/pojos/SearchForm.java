package team9.tutoragency.controller.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import team9.tutoragency.model.University;

public class SearchForm{
	
	public static final int DEFAULT_MIN_FEE = 0;
	public static final int DEFAULT_MAX_FEE = 40;
	public static final String DEFAULT_MIN_GRADE = "4.0";
	
	private String searchText;
	private boolean filtered;
	private int minFee;
	private int maxFee;
	private String minGrade;
	private List<String> universityNames;
	
	
	public SearchForm(String searchText, int minFee, int maxFee, List<String> universityNames) {
		this.searchText = searchText==null? "" : searchText;
		
		this.minFee = minFee;
		this.maxFee = maxFee;
		
		this.universityNames = universityNames==null? new ArrayList<String>(): universityNames;
		minGrade = DEFAULT_MIN_GRADE;
	}
	
	public SearchForm() {
		searchText= "";
		minFee = DEFAULT_MIN_FEE;
		maxFee = DEFAULT_MAX_FEE;
		this.universityNames = new ArrayList<String>();
		minGrade = DEFAULT_MIN_GRADE;
	}

	public SearchForm(String searchText) {
		this.searchText = searchText==null? "" : searchText;
		minFee = DEFAULT_MIN_FEE;
		maxFee = DEFAULT_MAX_FEE;
		this.universityNames = new ArrayList<String>();
		minGrade = DEFAULT_MIN_GRADE;
	}


	public String getMinGrade() {
		return minGrade;
	}

	public void setMinGrade(String minGrade) {
		this.minGrade = minGrade;
	}

	public int getMinFee() {
		return minFee;
	}
	public void setMinFee(int minFee) {
		this.minFee = minFee;
	}
	public int getMaxFee() {
		return maxFee;
	}
	public void setMaxFee(int maxFee) {
		this.maxFee = maxFee;
	}

	public boolean isFiltered() {
		return filtered;
	}

	public void setFiltered(boolean filtered) {
		this.filtered = filtered;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<String> getUniversityNames() {
		return universityNames;
	}

	public void setUniversityNames(List<String> universityNames) {
		this.universityNames = universityNames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (filtered ? 1231 : 1237);
		result = prime * result + maxFee;
		result = prime * result + minFee;
		result = prime * result + ((minGrade == null) ? 0 : minGrade.hashCode());
		result = prime * result + ((searchText == null) ? 0 : searchText.hashCode());
		result = prime * result + ((universityNames == null) ? 0 : universityNames.hashCode());
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
		SearchForm other = (SearchForm) obj;
		if (filtered != other.filtered)
			return false;
		if (maxFee != other.maxFee)
			return false;
		if (minFee != other.minFee)
			return false;
		if (minGrade == null) {
			if (other.minGrade != null)
				return false;
		} else if (!minGrade.equals(other.minGrade))
			return false;
		if (searchText == null) {
			if (other.searchText != null)
				return false;
		} else if (!searchText.equals(other.searchText))
			return false;
		if (universityNames == null) {
			if (other.universityNames != null)
				return false;
		} else if (!universityNames.equals(other.universityNames))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SearchForm [searchText=" + searchText + ", filtered=" + filtered + ", minFee=" + minFee + ", maxFee="
				+ maxFee + ", minGrade=" + minGrade + ", universityNames=" + universityNames + "]";
	}
	
	
	
}
