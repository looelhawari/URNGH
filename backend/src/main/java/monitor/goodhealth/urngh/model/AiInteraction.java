package monitor.goodhealth.urngh.model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AiInteraction {
    private final String apiKey; // API Key for authentication
    private final HttpClient httpClient; // HttpClient for making requests
    private final List<Map<String, String>> chatHistory; // Store chat history
    // Constructor to initialize the API key, HttpClient, and chat history
    public AiInteraction(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder().build(); // Initialize HttpClient
        this.chatHistory = new ArrayList<>(); // Initialize chat history
    }
    // Method to send a message to the Groq API and receive a response
    public String getResponse(String message) throws IOException, InterruptedException {
        String apiUrl = "https://api.groq.com/openai/v1/chat/completions"; // Groq API endpoint URL
        // Use a JSON library to construct the payload
        ObjectMapper objectMapper = new ObjectMapper();
        // Add user message to chat history
        chatHistory.add(Map.of("role", "user", "content", message));
        System.out.println("\n\n\n\n chat history:\n"+chatHistory+"\n\n\n\n");        // Create the JSON payload structure
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama3-8b-8192");  // Ensure you're using the correct model for chat-based responses
        requestBody.put("messages", chatHistory); // Use chat history in the payload
        // Convert the request body to a JSON string
        String payload = objectMapper.writeValueAsString(requestBody);
        // Log the JSON payload for debugging
        System.out.println("Payload: " + payload);
        // Create the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofString(payload))
            .uri(URI.create(apiUrl))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + apiKey)
            .build();
        // Send the request and get the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // Check for a successful response
        if (response.statusCode() != 200) {
            throw new IOException(
                "Unexpected response code: " + response.statusCode() + " Response: " + response.body());
        }
        // Parse the JSON response
        String aiResponse = parseResponse(response.body());
        // Add AI response to chat history
        chatHistory.add(Map.of("role", "assistant", "content", aiResponse));
        // Return the AI response
        return aiResponse;
    }
    // Method to parse the JSON response
    private String parseResponse(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            // Navigate to the response content (adjust the path as per the actual response format)
            String responseContent = rootNode
                .path("choices") // Example path, adjust if needed
                .get(0)
                .path("message")
                .path("content")
                .asText();
            return responseContent.replace("\\n", "\n"); // Handle newlines in the response
        } catch (Exception e) {
            return "Error parsing response: " + e.getMessage();
        }
    }
}
