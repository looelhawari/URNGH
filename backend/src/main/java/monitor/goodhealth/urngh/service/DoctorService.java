package monitor.goodhealth.urngh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import monitor.goodhealth.urngh.model.Doctor;
import monitor.goodhealth.urngh.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Register a new doctor
    public Doctor registerDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
