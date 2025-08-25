package monitor.goodhealth.urngh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import monitor.goodhealth.urngh.model.Patient;
import monitor.goodhealth.urngh.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Register a new patient
    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findById(Long patient) {
        return patientRepository.findById(patient)
        		.orElse(null);
    }
    // Update patient info
    public Patient updatePatientInfo(Long patientId, Patient patientInfo) {
        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        // Update fields
        existingPatient.setFullName(patientInfo.getFullName());
        existingPatient.setDateOfBirth(patientInfo.getDateOfBirth());
        existingPatient.setGender(patientInfo.getGender());
        existingPatient.setEmergencyContactNumber(patientInfo.getEmergencyContactNumber());
        existingPatient.setAddress(patientInfo.getAddress());
        existingPatient.setBloodType(patientInfo.getBloodType());
        existingPatient.setMedicalHistory(patientInfo.getMedicalHistory());
        existingPatient.setCurrentMedication(patientInfo.getCurrentMedication());
        existingPatient.setAllergies(patientInfo.getAllergies());

        return patientRepository.save(existingPatient);
    }
}
