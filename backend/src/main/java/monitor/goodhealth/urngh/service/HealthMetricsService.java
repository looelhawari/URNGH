package monitor.goodhealth.urngh.service;

import monitor.goodhealth.urngh.model.HealthMetrics;
import monitor.goodhealth.urngh.repository.HealthMetricsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Service
public class HealthMetricsService {

    private final HealthMetricsRepository healthMetricsRepository;

    @Autowired
    public HealthMetricsService(HealthMetricsRepository healthMetricsRepository) {
        this.healthMetricsRepository = healthMetricsRepository;
    }

    // Save health metrics to the database
    public HealthMetrics saveMetrics(HealthMetrics healthMetrics) {
        return healthMetricsRepository.save(healthMetrics);
    }

    // Get health metrics by patient ID
    public List<HealthMetrics> getMetricsByPatientId(Long patientId) {
        return healthMetricsRepository.findByPatientId(patientId);
    }

    // Generate a CSV report
    public byte[] generateReport() {
        List<HealthMetrics> metricsList = healthMetricsRepository.findAll();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream)) {
            // Write CSV header
            writer.write("Date,Blood Pressure,Blood Sugar,Heart Rate\n");

            // Write data rows
            for (HealthMetrics metrics : metricsList) {
                writer.write(metrics.getRecordedAt() + "," + 
                             (metrics.getBloodPressure() != null ? metrics.getBloodPressure() : "N/A") + "," +
                             (metrics.getBloodSugar() != null ? metrics.getBloodSugar() : "N/A") + "," +
                             (metrics.getHeartRate() != null ? metrics.getHeartRate() : "N/A") + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public boolean deleteHealthMetrics(Long metricId) {
        if (healthMetricsRepository.existsById(metricId)) {
            healthMetricsRepository.deleteById(metricId);
            return true;
        }
        return false;
    }

}
