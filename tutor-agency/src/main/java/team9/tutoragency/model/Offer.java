package team9.tutoragency.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"tutor_id", "course_id"}))
public class Offer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Member tutor;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	@OneToMany
	@JoinColumn(name = "offer_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Subscription> subscriptions = new ArrayList<Subscription>();
	
	private float grade;

	public Offer(Member tutor, Course course, float grade) {
		this.tutor = tutor;
		this.course = course;
		this.grade = grade;
	}
	public Offer(){}
	
	public Offer(Long id, Member tutor, Course course) {
		super();
		this.id = id;
		this.tutor = tutor;
		this.course = course;
	}
	public Offer(Long id, Member tutor, Course course, float grade) {
		super();
		this.id = id;
		this.tutor = tutor;
		this.course = course;
		this.grade = grade;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getTutor() {
		return tutor;
	}

	public void setTutor(Member tutor) {
		this.tutor = tutor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + Float.floatToIntBits(grade);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tutor == null) ? 0 : tutor.hashCode());
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
		Offer other = (Offer) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (Float.floatToIntBits(grade) != Float.floatToIntBits(other.grade))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tutor == null) {
			if (other.tutor != null)
				return false;
		} else if (!tutor.equals(other.tutor))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", tutor=" + tutor + ", course=" + course + ", grade=" + grade + "]";
	}
	
	/**
	 * Returns a list of the string-representations for the possible grades.
	 * @return
	 */
	@Transient
	public static List<String> possibleGrades() {
		List<String> grades = new ArrayList<String>();
		for (float i = 4; i <= 6; i += 0.25) {
			grades.add(Float.toString(i));
		}
		return grades;
	}

}
