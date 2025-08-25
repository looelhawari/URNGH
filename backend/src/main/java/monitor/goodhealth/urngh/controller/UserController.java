package monitor.goodhealth.urngh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import monitor.goodhealth.urngh.model.Doctor;
import monitor.goodhealth.urngh.model.Patient;
import monitor.goodhealth.urngh.model.User;
import monitor.goodhealth.urngh.service.PatientService;
import monitor.goodhealth.urngh.service.DoctorService;
import monitor.goodhealth.urngh.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    // Register a new patient
    @PostMapping("/registerPatient")
    public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {
        try {
            Patient savedPatient = patientService.registerPatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ErrorResponse("Error: " + e.getMessage()));
        }
    }
    // Register a new doctor
    @PostMapping("/registerDoctor")
    public ResponseEntity<?> registerDoctor(@RequestBody Doctor doctor) {
        System.out.print(doctor);
        try {
            Doctor savedDoctor = doctorService.registerDoctor(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ErrorResponse("Error: " + e.getMessage()));
        }
    }
    // Authentication logic (login)
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginDetails) {
        try {
            // Authenticate the user using the service
            User authenticatedUser = userService.authenticateUser(loginDetails.getEmail(), loginDetails.getPassword());
            if (authenticatedUser != null) {
                // Return the authenticated user as the response
                return ResponseEntity.ok(authenticatedUser);
            } else {
                // Return unauthorized if authentication failed
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception e) {
            // Handle any unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Authentication failed: " + e.getMessage());
        }
    }
    // Error response structure
    public static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
