package monitor.goodhealth.urngh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import monitor.goodhealth.urngh.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	Optional<Patient> findByEmail(String email);
}
