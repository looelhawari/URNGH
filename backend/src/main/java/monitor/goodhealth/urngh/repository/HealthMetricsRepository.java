package monitor.goodhealth.urngh.repository;

import monitor.goodhealth.urngh.model.HealthMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthMetricsRepository extends JpaRepository<HealthMetrics, Long> {
    // Find metrics by patient ID
    List<HealthMetrics> findByPatientId(Long patientId);
}
