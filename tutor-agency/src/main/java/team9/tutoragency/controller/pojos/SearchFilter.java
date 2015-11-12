package team9.tutoragency.controller.pojos;

import java.util.List;

import team9.tutoragency.model.University;

public class SearchFilter {

	private int minFee;
	private int maxFee;
	private List<University> universities;
	public SearchFilter(int minFee, int maxFee, List<University> universities) {
		this.minFee = minFee;
		this.maxFee = maxFee;
		this.universities = universities;
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
	public List<University> getUniversities() {
		return universities;
	}
	public void setUniversities(List<University> universities) {
		this.universities = universities;
	}
	
}
