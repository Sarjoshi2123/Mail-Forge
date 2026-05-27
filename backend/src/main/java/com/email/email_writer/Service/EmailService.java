package com.email.email_writer.Service;

import com.email.email_writer.Model.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateEmailReply(Email email) {
        //build the prompt
        String prompt = buildPrompt(email);

        //craft the request
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "role", "user",
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        System.out.println("Gemini api key :" + geminiApiKey);
        System.out.println("Gemini api url :" + geminiApiUrl);
        String full = geminiApiUrl +"?key="+ geminiApiKey;
        System.out.println("Gemini api url :" + full);

        //get request and generate the response
//        String response = webClient.post()
//                .uri(full)
//                .header("Content-Type","application/json")
//                .bodyValue(requestBody)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();

        String response = webClient.post()
                .uri(full)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.isError(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Gemini Error: " + body)))
                .bodyToMono(String.class)
                .block();

        //extract and return response
        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            return jsonNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        } catch(Exception e)
        {
            return "Error getting the email response " + e.getMessage();
        }
    }

    private String buildPrompt(Email email) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional for the following email content. Please don't enter the subject Line ");

        if (email.getTone() != null && !email.getTone().isEmpty()) {
            prompt.append("Use a ").append(email.getTone()).append(" tone.");
        }
        prompt.append("\nOrigional Email :\n").append(email.getEmailContent());
        return prompt.toString();
    }
}
