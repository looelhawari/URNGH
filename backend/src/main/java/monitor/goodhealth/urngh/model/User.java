package monitor.goodhealth.urngh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user") // Replace with your desired table name
public class User {

	public User() {
		super();
	}

	public User(Long id, @NotNull String username, @NotNull String password, @NotNull @Email String email, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullName;
	private String contactNumber; // New field

	private boolean notificationsEnabled; // New field for notifications
	private String preferredCommunicationMethod; // New field for preferred communication method
	private boolean healthDataVisible; // New field for privacy
	private boolean shareDataWithThirdParties; // New field for privacy
	private String language; // New field for app preferences
	private boolean darkModeEnabled; // New field for app preferences
	private String emergencyContact; // New field for emergency contact
	private boolean isActive; // New field for account status

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	@Column(unique = true)
	@Email
	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	public enum Role {
		PATIENT, DOCTOR
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}



	public boolean isNotificationsEnabled() {
		return notificationsEnabled;
	}

	public void setNotificationsEnabled(boolean notificationsEnabled) {
		this.notificationsEnabled = notificationsEnabled;
	}

	public String getPreferredCommunicationMethod() {
		return preferredCommunicationMethod;
	}

	public void setPreferredCommunicationMethod(String preferredCommunicationMethod) {
		this.preferredCommunicationMethod = preferredCommunicationMethod;
	}

	public boolean isHealthDataVisible() {
		return healthDataVisible;
	}

	public void setHealthDataVisible(boolean healthDataVisible) {
		this.healthDataVisible = healthDataVisible;
	}

	public boolean isShareDataWithThirdParties() {
		return shareDataWithThirdParties;
	}

	public void setShareDataWithThirdParties(boolean shareDataWithThirdParties) {
		this.shareDataWithThirdParties = shareDataWithThirdParties;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isDarkModeEnabled() {
		return darkModeEnabled;
	}

	public void setDarkModeEnabled(boolean darkModeEnabled) {
		this.darkModeEnabled = darkModeEnabled;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
				+ role + "]";
	}
}
