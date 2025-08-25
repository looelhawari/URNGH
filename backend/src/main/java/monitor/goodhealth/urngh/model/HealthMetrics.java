package monitor.goodhealth.urngh.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "health_metrics")
public class HealthMetrics {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference
    private Patient patient;

    @Column(name = "blood_pressure", nullable = true)
    private String bloodPressure;

    @Column(name = "blood_sugar", nullable = true)
    private String bloodSugar;

    @Column(name = "heart_rate", nullable = true)
    private String heartRate;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    // Constructors
    public HealthMetrics() {}

    public HealthMetrics(Patient patient, String bloodPressure, String bloodSugar, String heartRate) {
        this.patient = patient;
        this.bloodPressure = bloodPressure;
        this.bloodSugar = bloodSugar;
        this.heartRate = heartRate;
        this.recordedAt = LocalDateTime.now();
    }

    @Override
	public String toString() {
		return "HealthMetrics [id=" + id + ", patient=" + patient + ", bloodPressure=" + bloodPressure + ", bloodSugar="
				+ bloodSugar + ", heartRate=" + heartRate + ", recordedAt=" + recordedAt + "]";
	}

	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

	public String generateSummary() {
		// TODO Auto-generated method stub
		return null;
	}
}
