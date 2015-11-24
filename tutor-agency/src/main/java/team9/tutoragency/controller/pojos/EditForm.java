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
}
