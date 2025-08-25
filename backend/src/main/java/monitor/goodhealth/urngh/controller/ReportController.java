package monitor.goodhealth.urngh.controller;

import monitor.goodhealth.urngh.model.Report;
import monitor.goodhealth.urngh.model.Patient;
import monitor.goodhealth.urngh.service.ReportService;
import monitor.goodhealth.urngh.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private PatientService patientService;
    @CrossOrigin
    @GetMapping("/patient/{patientId}")
    public List<Report> getReportsByPatient(@PathVariable Long patientId) {
        return reportService.getReportsByPatientId(patientId);
    }
    @CrossOrigin
    @PostMapping
    public Report createReport(@RequestBody Long report) {
        try {
            return reportService.createReportFromMetrics(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save the report as a file.", e);
        }
    }
    @CrossOrigin
    @PostMapping("/patient/{patientId}")
    public Report createReportFromMetrics(@PathVariable Long patientId) {
        try {
            return reportService.createReportFromMetrics(patientId);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save the report as a file.", e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @CrossOrigin
    @GetMapping("/patient/{patientId}/detailed-csv")
    public ResponseEntity<byte[]> downloadDetailedReport(@PathVariable Long patientId) {
        try {
            byte[] report = reportService.generateDetailedReport(patientId);
            return ResponseEntity.ok()
                .header("Content-Type", "text/csv")
                .header("Content-Disposition", "attachment; filename=\"detailed_health_metrics_report.csv\"")
                .body(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate the report.", e);
        }
    }
}
