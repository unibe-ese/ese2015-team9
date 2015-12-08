package team9.tutoragency.controller.pojos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import team9.tutoragency.controller.service.validation.SignupFormValidator;
import team9.tutoragency.model.Member;

/**
 * A SignupForm contains all values which need to be set in order to allow a
 * user to register as a {@link Member} in the register view. The SignupForm
 * will be validated by the {@link SignupFormValidator}.
 * 
 * @author laeri
 * @author curtys
 *
 */
public class SignupForm extends BasicMemberForm{

	private boolean readAGB;
	@NotNull(message = "Please enter a password.")
	@Size(min = 4, max = 10, message = "Password needs to be 4-10 characters long")
	private String password;
	@NotNull(message = "Please enter a matching password.")
	private String passwordConfirm;
	
	public SignupForm() {
		super();	
		password = "";
		passwordConfirm = "";
	}

	public boolean isReadAGB() {
		return readAGB;
	}

	public void setReadAGB(boolean readAGB) {
		this.readAGB = readAGB;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordConfirm == null) ? 0 : passwordConfirm.hashCode());
		result = prime * result + (readAGB ? 1231 : 1237);
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
		SignupForm other = (SignupForm) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "SignupForm [readAGB=" + readAGB + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ "]";
	} 
}
