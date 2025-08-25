package monitor.goodhealth.urngh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import monitor.goodhealth.urngh.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Optional <Doctor> findByEmail(String email);
}
