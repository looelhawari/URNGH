package monitor.goodhealth.urngh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import monitor.goodhealth.urngh.model.Emergency;

public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
	 List<Emergency> findByUserId(Long userId);
}
