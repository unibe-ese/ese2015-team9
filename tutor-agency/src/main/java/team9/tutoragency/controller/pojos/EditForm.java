package team9.tutoragency.controller.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import team9.tutoragency.controller.service.validation.EditFormValidator;
import team9.tutoragency.model.Member;

/**
 * The EditForm stores all values to be displayed in the edit view to edit a
 * profile of a {@link Member}. The EditForm will be validated by the
 * {@link EditFormValidator}.
 * 
 * @author laeri
 * @author curtys
 *
 */
public class EditForm extends BasicMemberForm{

	@Size(max = 2000, message = "Description is to long.")
	private String description;

	private List<String> universities;

	@NotNull(message = "Please enter your old Password")
	private String oldPassword;

	private String password;
	private String passwordConfirm;
	
	private String fee;

	private boolean readAGB;

	public EditForm() {
		super();
		password = "";
		passwordConfirm = "";
		oldPassword = "";
		universities = new ArrayList<>();

	}

	
	public EditForm(Member member){
		super(member);
		description = member.getDescription();
		if (member.getFee() != null) 
			fee = member.getFee().toString();
		else 
			fee = "0";
		oldPassword = "";
		password = "";
		passwordConfirm = "";
		universities = new ArrayList<>();
			
		
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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

	public List<String> getUniversities() {
		if (universities == null)
			universities = new ArrayList<>();
		return universities;
	}

	public void setUniversities(List<String> universities) {
		this.universities = universities;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "EditForm [description=" + description + ", universities=" + universities + ", oldPassword="
				+ oldPassword + ", password=" + password + ", passwordConfirm=" + passwordConfirm + ", fee=" + fee
				+ ", readAGB=" + readAGB + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fee == null) ? 0 : fee.hashCode());
		result = prime * result + ((oldPassword == null) ? 0 : oldPassword.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordConfirm == null) ? 0 : passwordConfirm.hashCode());
		result = prime * result + (readAGB ? 1231 : 1237);
		result = prime * result + ((universities == null) ? 0 : universities.hashCode());
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
		EditForm other = (EditForm) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fee == null) {
			if (other.fee != null)
				return false;
		} else if (!fee.equals(other.fee))
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
		return true;
	}  
}
