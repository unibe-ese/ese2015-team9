
package team9.tutoragency.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import team9.tutoragency.controller.RegisterController;

/**
 * <p>
 * A {@link Member} is a user in the domain model of the Tutoring-Agency which
 * tries to put tutors in contact with students. Both students and tutors are
 * members but a tutor has a true {@link #isTutor} entry which can be attained
 * by "upgrading" the account on the profile page of the Tutor-Agency website.
 * In order to become a {@link Member} a user can register
 * {@link RegisterController} in the register view.
 * </p>
 * <p>
 * A {@link Member} has to supply
 * <ul>
 * <li>a first name {@link #firstName}</li>
 * <li>a last name {@link #lastName}</li>
 * <li>a username {@link #username} which has to be unique for every member</li>
 * <li>an email address {@link #email} which should also be unique</li>
 * <li>a password {@link #password}</li>
 * </ul>
 * The fee {@link #fee} only has to be specified if the {@link Member} has
 * upgraded his account to a tutor. The {@link #courseList} contains a list of
 * courses a tutor can offer to a student.
 * 
 * </p>
 * 
 * 
 * @author laeri
 *
 */
@Entity
public class Member implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private Double fee; 
	private boolean isTutor;
	private boolean isActivated;

	@ManyToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Course> courseList;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<University> universityList;

	/**
	 * To provide a cleaner construction in the test, when only selected values are important. 
	 * @author brn
	 */
	public static class Builder{
		private Long id;
		private String firstName;
		private String lastName;
		private String email;
		private String username;
		private String password;
		private Double fee; 
		private boolean isTutor;
		private boolean isActivated;
		private List<Course> courseList;
		private List<University> universityList;
		
		public Builder(){}
		public Member build(){
			if(universityList==null)
				universityList = new ArrayList<University>();
			if(courseList==null)
				courseList = new ArrayList<Course>();
			return new Member(this);
		}
		public Builder id(Long id){
			this.id=id;
			return this;
		}
		public Builder firstName(String firstName){
			this.firstName = firstName;
			return this;
		}
		public Builder lastName(String lastName){
			this.lastName = lastName;
			return this;
		}
		public Builder email(String email){
			this.email = email;
			return this;
		}
		public Builder username(String username){
			this.username = username;
			return this;
		}
		public Builder password(String password){
			this.password = password;
			return this;
		}
		public Builder fee(Double fee){
			this.fee = fee;
			return this;
		}
		public Builder isTutor(boolean tutor){
			isTutor = tutor;
			return this;
		}
		public Builder isActivated(boolean activated){
			isActivated = activated;
			return this;
		}
		public Builder courseList(List<Course> courseList){
			this.courseList = courseList;
			return this;
		}
		public Builder universityList(List<University> universityList){
			this.universityList = universityList;
			return this;
		}
	}
	public Member(String firstName, String lastName, String email, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.username = username;
		this.isTutor = false;
		this.isActivated = false;
		this.fee = null;
	}
	
	
	

	public Member() {

	}

	public Member(Builder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.username = builder.username;
		this.email = builder.email;
		this.password = builder.password;
		this.fee = builder.fee;
		this.isTutor = builder.isTutor;
		this.isActivated = builder.isActivated;
		this.courseList = builder.courseList;
		this.universityList = builder.universityList;
	}


	/**
	 * We know that the name is ugly, but it makes more sense in the jsp file ;-) .
	 */
	public boolean isIsTutor() {
		return isTutor;
	}
	public void setIsTutor(boolean tutor) {
		this.isTutor = tutor;
	}
	/**
	 * We know that the name is ugly, but it makes more sense in the jsp file ;-) .
	 */
	public boolean isIsActivated() {
		return isActivated;
	}
	public void setIsActivated(boolean activated) {
		this.isActivated = activated;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public List<Course> getCourseList() {
		if (courseList == null)
			courseList = new ArrayList<Course>();
		return courseList;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public List<University> getUniversityList() {
		return universityList;
	}

	public void setUniversityList(List<University> universityList) {
		this.universityList = universityList;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_USER"));
		return list;

	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseList == null) ? 0 : courseList.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fee == null) ? 0 : fee.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isActivated ? 1231 : 1237);
		result = prime * result + (isTutor ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((universityList == null) ? 0 : universityList.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Member other = (Member) obj;
		if (courseList == null) {
			if (other.courseList != null)
				return false;
		} else if (!courseList.equals(other.courseList))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fee == null) {
			if (other.fee != null)
				return false;
		} else if (!fee.equals(other.fee))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActivated != other.isActivated)
			return false;
		if (isTutor != other.isTutor)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (universityList == null) {
			if (other.universityList != null)
				return false;
		} else if (!universityList.equals(other.universityList))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Member{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", isTutor=" + isTutor + ", fee=" + fee
				+ ", isActivated=" + isActivated + ", courseList=" + courseList + ", universityList=" + universityList
				+ '}';
	}

}
