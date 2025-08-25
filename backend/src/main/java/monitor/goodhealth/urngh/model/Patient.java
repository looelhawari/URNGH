package monitor.goodhealth.urngh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Patient extends User {

    private LocalDate dateOfBirth;
    private String gender;

    @Column(name="emergeny_contact_number")
    private String emergencyContactNumber;

    @Column(name = "address", nullable = true)
    private String address;
    
    private String bloodType;
    
    @Column(name = "doctor_contact")
    private String doctorContactNumber;

    public String getDoctorContactNumber() {
		return doctorContactNumber;
	}

	public void setDoctorContactNumber(String doctorContactNumber) {
		this.doctorContactNumber = doctorContactNumber;
	}

	@Lob
    private String medicalHistory;

    @Lob
    private String currentMedication;

    @Lob
    private String allergies;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HealthMetrics> healthMetrics;

    // Constructors
    public Patient(Long id, @NotNull String username, @NotNull String password, @NotNull @Email String email, Role role) {
        super(id, username, password, email, role);
        // Default role for patient
        if (role == null) {
            this.setRole(Role.PATIENT);
        }
    }

    public Patient() {
        super();
        this.setRole(Role.PATIENT);
    }

    // Getters and Setters
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentMedication() {
        return currentMedication;
    }

    public void setCurrentMedication(String currentMedication) {
        this.currentMedication = currentMedication;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public List<HealthMetrics> getHealthMetrics() {
        return healthMetrics;
    }

    public void setHealthMetrics(List<HealthMetrics> healthMetrics) {
        this.healthMetrics = healthMetrics;
    }
}
