package monitor.goodhealth.urngh.controller;

import org.springframework.web.bind.annotation.*;
import monitor.goodhealth.urngh.service.aiService;
import java.io.IOException;

@CrossOrigin
@RestController
public class AiController {
    private final aiService aiInteractionService;

    public AiController(aiService aiInteractionService) {
        this.aiInteractionService = aiInteractionService;  // Inject the service
    }
    @CrossOrigin
    @GetMapping("/response/{message}")
    public void getAInteraction(@PathVariable String message) {
        System.out.println("message: " + message);
    }
    @CrossOrigin
    @PostMapping("/response")
    public String getAiResponse(@RequestBody String message) {
        System.out.println(message);
        try {
            return aiInteractionService.getAiResponse(message);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error occurred while getting AI response: " + e.getMessage();
        }
    }
}
