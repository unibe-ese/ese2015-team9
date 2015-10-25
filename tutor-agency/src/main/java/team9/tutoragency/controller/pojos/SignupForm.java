package team9.tutoragency.controller.pojos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class SignupForm {

	private Long id;
	@NotNull(message="Please enter your first name.")
	private String firstName;
	@NotNull(message="Please enter your last name.")
	private String lastName;
	@NotNull(message="Please enter a username.")
	private String username;
	@NotNull(message="Please enter a password.")
	@Size(min=6 , max=18, message="Passwort sollte aus 6-18 Zeichen bestehen")
	private String password;
	@NotNull(message="Please enter a matching password.")
	private String passwordConfirm;
	@NotNull
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Must be valid email address")
	private String email;

	private boolean readAGB;
	
	public SignupForm(){
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

}
