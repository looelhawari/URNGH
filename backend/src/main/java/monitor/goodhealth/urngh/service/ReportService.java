package monitor.goodhealth.urngh.service;

import monitor.goodhealth.urngh.model.Patient;
import monitor.goodhealth.urngh.model.HealthMetrics;
import monitor.goodhealth.urngh.model.Report;
import monitor.goodhealth.urngh.repository.HealthMetricsRepository;
import monitor.goodhealth.urngh.repository.PatientRepository;
import monitor.goodhealth.urngh.repository.ReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HealthMetricsRepository healthMetricsRepository;

    @Autowired
    private ReportRepository reportRepository;

    public Report createReportFromMetrics(Long patientId) throws IOException {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        List<HealthMetrics> metricsList = healthMetricsRepository.findByPatientId(patientId);

        // Create the report content
        StringBuilder content = new StringBuilder();
        content.append("Patient Name: ").append(patient.getFullName()).append("\n");
        content.append("Age: ").append(patient.getId()).append("\n");
        content.append("Email: ").append(patient.getEmail()).append("\n\n");
        content.append("Health Metrics:\n");
        content.append("Recorded At, Blood Pressure, Blood Sugar, Heart Rate\n");
        for (HealthMetrics metrics : metricsList) {
            content.append(metrics.getRecordedAt()).append(", ")
                   .append(metrics.getBloodPressure()).append(", ")
                   .append(metrics.getBloodSugar()).append(", ")
                   .append(metrics.getHeartRate()).append("\n");
        }

        // Save the report to a file (or implement any other logic to handle the report)
        String fileName = "Report_" + patientId + "_" + LocalDateTime.now() + ".txt";
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content.toString());
        }

        // Save the report details to the database
        Report report = new Report();
        
        report.setContent(content.toString());
        report.setCreatedAt(LocalDateTime.now());

        return reportRepository.save(report);
    }

    public List<Report> getReportsByPatientId(Long patientId) {
        return reportRepository.findByPatientId(patientId);
    }

    public byte[] generateDetailedReport(Long patientId) throws IOException {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        List<HealthMetrics> metricsList = healthMetricsRepository.findByPatientId(patientId);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(baos);

        // Write CSV header
        writer.println("Name, Age, Email, Blood Pressure, Blood Sugar, Heart Rate, Recorded At");

        // Write patient and health metrics data
        for (HealthMetrics metrics : metricsList) {
            writer.printf("%s, %d, %s, %s, %s, %s, %s%n",
                patient.getFullName(),
                patient.getId(),
                patient.getEmail(),
                metrics.getBloodPressure(),
                metrics.getBloodSugar(),
                metrics.getHeartRate(),
                metrics.getRecordedAt());
        }

        writer.flush();
        return baos.toByteArray();
    }
}
