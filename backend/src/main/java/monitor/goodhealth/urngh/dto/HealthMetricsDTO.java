package monitor.goodhealth.urngh.dto;

import java.time.LocalDateTime;

public class HealthMetricsDTO {
	private Long patientId;
	private String bloodPressure;
	private String bloodSugar;
	private String heartRate;
	private String recordedAt;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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

	public String getRecordedAt() {
		return recordedAt;
	}

	public void setRecordedAt(String recordedAt) {
		this.recordedAt = recordedAt;
	}
}
