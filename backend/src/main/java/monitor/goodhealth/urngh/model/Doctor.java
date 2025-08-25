package monitor.goodhealth.urngh.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class Doctor extends User {
	private String contactNumber;
	public Doctor(Long id, @NotNull String username, @NotNull String password, @NotNull @Email String email,
			Role role) {
		super(id, username, password, email, role);
		// TODO Auto-generated constructor stub
	}
	public Doctor() {
		super();
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
