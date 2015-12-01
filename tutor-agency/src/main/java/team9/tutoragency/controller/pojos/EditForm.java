package team9.tutoragency.controller.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import team9.tutoragency.controller.service.validation.EditFormValidationService;
import team9.tutoragency.model.Course;
import team9.tutoragency.model.Member;

/**
 * The EditForm stores all values to be displayed in the edit view to edit a
 * profile of a {@link Member}. The EditForm will be validated by the
 * {@link EditFormValidationService}.
 * 
 * @author laeri
 *
 */
public class EditForm implements Form {

	private Long id;
	private String firstName;
	@NotNull(message = "Please enter your last name.")
	private String lastName;
	@NotNull(message = "Please enter a username.")
	private String username;

	private List<String> universities;

	@NotNull(message = "Please enter your old Password")
	private String oldPassword;

	private String password;
	private String passwordConfirm;

	private String fee;

	@NotNull
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Must be valid email address")
	private String email;

	private boolean readAGB;

	public EditForm() {
		oldPassword = "";
		password = "";
		passwordConfirm = "";
		universities = new ArrayList<String>();

	}

	
	public EditForm(Member member){
		id = member.getId();
		firstName = member.getFirstName();
		lastName = member.getLastName();
		username = member.getUsername();
		email = member.getEmail();
		if (member.getFee() != null) 
			fee = member.getFee().toString();
		else 
			fee = "0";
		oldPassword = "";
		password = "";
		passwordConfirm = "";
		universities = new ArrayList<String>();
			
		
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isReadAGB() {
		return readAGB;
	}

	public void setReadAGB(boolean readAGB) {
		this.readAGB = readAGB;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public List<String> getUniversities() {
		if (universities == null)
			universities = new ArrayList<String>();
		return universities;
	}

	public void setUniversities(List<String> universities) {
		this.universities = universities;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fee == null) ? 0 : fee.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((oldPassword == null) ? 0 : oldPassword.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordConfirm == null) ? 0 : passwordConfirm.hashCode());
		result = prime * result + (readAGB ? 1231 : 1237);
		result = prime * result + ((universities == null) ? 0 : universities.hashCode());
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
		EditForm other = (EditForm) obj;
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
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (oldPassword == null) {
			if (other.oldPassword != null)
				return false;
		} else if (!oldPassword.equals(other.oldPassword))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordConfirm == null) {
			if (other.passwordConfirm != null)
				return false;
		} else if (!passwordConfirm.equals(other.passwordConfirm))
			return false;
		if (readAGB != other.readAGB)
			return false;
		if (universities == null) {
			if (other.universities != null)
				return false;
		} else if (!universities.equals(other.universities))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
