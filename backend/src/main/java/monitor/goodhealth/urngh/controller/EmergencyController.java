package monitor.goodhealth.urngh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import monitor.goodhealth.urngh.model.Emergency;
import monitor.goodhealth.urngh.service.EmergencyService;

@RestController
@RequestMapping("/api/emergencies")
public class EmergencyController {
    @Autowired
    private EmergencyService emergencyService;
    @PostMapping("/")
    public Emergency createEmergency(@RequestBody Emergency emergency) {
        return emergencyService.createEmergency(emergency);
    }
}
