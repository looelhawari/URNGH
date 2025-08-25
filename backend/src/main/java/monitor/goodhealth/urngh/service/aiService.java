package monitor.goodhealth.urngh.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import monitor.goodhealth.urngh.model.AiInteraction;

import java.io.IOException;

@Service
public class aiService {

	private final AiInteraction AiInteraction;

	// Constructor injection for the API key from application.properties
	public aiService(@Value("${groq.api.key}") String apiKey) {
		this.AiInteraction = new AiInteraction(apiKey); // Initialize AiInteraction with API key
	}

	// Method to get a response from the AI
	public String getAiResponse(String message) throws IOException, InterruptedException {
		return AiInteraction.getResponse(message); // Call your AiInteraction method
	}
}
