package monitor.goodhealth.urngh.service;

import monitor.goodhealth.urngh.model.Emergency;
import monitor.goodhealth.urngh.repository.EmergencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmergencyService {

    private final EmergencyRepository emergencyRepository;

    @Autowired
    public EmergencyService(EmergencyRepository emergencyRepository) {
        this.emergencyRepository = emergencyRepository;
    }

    // Save emergency record to the database
    public Emergency saveEmergency(Emergency emergency) {
        return emergencyRepository.save(emergency);
    }

    // Get all emergency records
    public List<Emergency> getAllEmergencies() {
        return emergencyRepository.findAll();
    }

    // Get emergency records by user ID
    public List<Emergency> getEmergenciesByUserId(Long userId) {
        return emergencyRepository.findByUserId(userId);
    }

    // Get a specific emergency record by its ID
    public Optional<Emergency> getEmergencyById(Long emergencyId) {
        return emergencyRepository.findById(emergencyId);
    }

    // Delete emergency record by ID
    public boolean deleteEmergency(Long emergencyId) {
        if (emergencyRepository.existsById(emergencyId)) {
            emergencyRepository.deleteById(emergencyId);
            return true;
        }
        return false;
    }

	public Emergency createEmergency(Emergency emergency) {
		// TODO Auto-generated method stub
		return null;
	}
}
