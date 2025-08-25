package monitor.goodhealth.urngh.controller;

import monitor.goodhealth.urngh.dto.HealthMetricsDTO;
import monitor.goodhealth.urngh.model.HealthMetrics;
import monitor.goodhealth.urngh.model.Patient;
import monitor.goodhealth.urngh.service.HealthMetricsService;
import monitor.goodhealth.urngh.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/metrics")
public class HealthMetricsController {
    private final HealthMetricsService healthMetricsService;
    @Autowired
    private PatientService patientService;
    @Autowired
    public HealthMetricsController(HealthMetricsService healthMetricsService) {
        this.healthMetricsService = healthMetricsService;
    }
    // Endpoint to add new health metrics
    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createHealthMetrics(@RequestBody HealthMetricsDTO request) {
        Patient patient = patientService.findById(request.getPatientId());
        HealthMetrics healthMetrics = new HealthMetrics();
        healthMetrics.setPatient(patient);
        healthMetrics.setBloodPressure(request.getBloodPressure());
        healthMetrics.setBloodSugar(request.getBloodSugar());
        healthMetrics.setHeartRate(request.getHeartRate());
        healthMetrics.setRecordedAt(LocalDateTime.parse(request.getRecordedAt()));
        healthMetricsService.saveMetrics(healthMetrics);
        return ResponseEntity.status(HttpStatus.CREATED).body("Health metrics created");
    }
    // Endpoint to download the report in CSV format
    @CrossOrigin
    @GetMapping("/report")
    public ResponseEntity<byte[]> downloadReport() {
        byte[] report = healthMetricsService.generateReport();
        return ResponseEntity.ok()
                .header("Content-Type", "text/csv")
                .header("Content-Disposition", "attachment; filename=\"health_metrics_report.csv\"")
                .body(report);
    }
    // Endpoint to get metrics by patient ID
    @CrossOrigin
    @GetMapping("/{patientId}")
    public List<HealthMetrics> getMetricsByPatientId(@PathVariable Long patientId) {
        return healthMetricsService.getMetricsByPatientId(patientId);
    }
    @CrossOrigin
    @DeleteMapping("/{metricId}")
    public ResponseEntity<String> deleteHealthMetrics(@PathVariable Long metricId) {
        boolean deleted = healthMetricsService.deleteHealthMetrics(metricId);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Metric deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Metric not found");
    }
}
