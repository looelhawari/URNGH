package monitor.goodhealth.urngh.repository;

import monitor.goodhealth.urngh.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // Find reports by patient ID
    List<Report> findByPatientId(Long patientId);
}
