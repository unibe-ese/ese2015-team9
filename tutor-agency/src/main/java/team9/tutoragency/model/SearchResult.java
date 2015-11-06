package team9.tutoragency.model;

/**
 * This wrapper class contains a {@link course} and a list of all
 * {@link Member}'s who are related to this course in the database. It would
 * be obsolete if we were able to make the course-member relation
 * bidirectional, then we could return the course directly.
 */
import java.util.ArrayList;
import java.util.List;

public class SearchResult {

	private Course course;
	private List<Member> members;

	public SearchResult() {
		course = new Course();
		members = new ArrayList<Member>();
	}

	public SearchResult(Course course, List<Member> members) {
		this.course = course;
		this.members = members;
	}

	public List<Member> getMembers() {
		return members;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((members == null) ? 0 : members.hashCode());
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
		SearchResult other = (SearchResult) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SearchResult [course=" + course + ", members=" + members + "]";
	}

}
