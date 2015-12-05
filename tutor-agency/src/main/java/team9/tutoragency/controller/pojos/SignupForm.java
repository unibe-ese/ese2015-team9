package team9.tutoragency.controller.pojos;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
public class SignupForm implements Form {

	private Long id;
	@NotNull(message = "Please enter your first name.")
	private String firstName;
	@NotNull(message = "Please enter your last name.")
	private String lastName;
	@NotNull(message = "Please enter a username.")
	private String username;
	@NotNull(message = "Please enter a password.")
	@Size(min = 6, max = 18, message = "Password needs to be 6-18 characters long")
	private String password;
	@NotNull(message = "Please enter a matching password.")
	private String passwordConfirm;
	@NotNull
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
            + "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", 
            message = "Must be valid email address")
	private String email;

	private boolean readAGB;

	public SignupForm() {
		firstName = "Vorname";
		lastName = "Nachname";
		username = "Nickname";
		email = "E-mail";
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.lastName);
        hash = 23 * hash + Objects.hashCode(this.username);
        hash = 23 * hash + Objects.hashCode(this.password);
        hash = 23 * hash + Objects.hashCode(this.passwordConfirm);
        hash = 23 * hash + Objects.hashCode(this.email);
        hash = 23 * hash + (this.readAGB ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SignupForm other = (SignupForm) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.passwordConfirm, other.passwordConfirm)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (this.readAGB != other.readAGB) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "SignupForm [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", passwordConfirm=" + passwordConfirm + ", email=" + email + ", readAGB="
				+ readAGB + "]";
	}
    
}
