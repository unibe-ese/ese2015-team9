package team9.tutoragency.model;

import java.util.Collection;
import static java.util.Arrays.asList;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@MappedSuperclass
public class AccountDetails extends User {

	@NotNull(message = "Please enter your first name.")
	private String firstName;
	@NotNull(message = "Please enter your last name.")
	private String lastName;

	@NotNull
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
            + "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", 
            message = "Must be valid email address")
	private String email;
	
	public AccountDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, asList(new SimpleGrantedAuthority("ROLE_USER")));
	}

	public AccountDetails(){
		super("", "", asList(new SimpleGrantedAuthority("ROLE_USER")));

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

	

}
