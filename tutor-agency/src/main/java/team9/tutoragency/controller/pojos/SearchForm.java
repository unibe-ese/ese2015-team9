package team9.tutoragency.controller.pojos;

import java.util.List;

import team9.tutoragency.model.University;

public class SearchForm extends QuickSearchForm{
	public static final int DEFAULT_MIN_FEE = 0;
	public static final int DEFAULT_MAX_FEE = 30; 
	
	private boolean filtered;
	private int minFee;
	private int maxFee;
	private List<String> universityNames;
	
	public SearchForm(String searchText, int minFee, int maxFee, List<String> universityNames) {
		super(searchText==null? "" : searchText);
		this.minFee = minFee;
		this.maxFee = maxFee;
		this.universityNames = universityNames;
	}
	
	public SearchForm() {
		super("");
		minFee = DEFAULT_MIN_FEE;
		maxFee = DEFAULT_MAX_FEE;
	}

	public List<String> getUniversityNames() {
		return universityNames;
	}

	public void setUniversityNames(List<String> universityNames) {
		this.universityNames = universityNames;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (filtered ? 1231 : 1237);
		result = prime * result + maxFee;
		result = prime * result + minFee;
		result = prime * result + ((universityNames == null) ? 0 : universityNames.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
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
		if (universityNames == null) {
			if (other.universityNames != null)
				return false;
		} else if (!universityNames.equals(other.universityNames))
			return false;
		return true;
	}

	public boolean isFiltered() {
		return filtered;
	}

	public void setFiltered(boolean filtered) {
		this.filtered = filtered;
	}
	
}
