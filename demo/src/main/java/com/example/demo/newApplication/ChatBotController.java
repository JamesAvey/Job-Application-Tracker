package com.example.demo.newApplication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatBotController {

    @Value("${openai.api.key}")
    private String apiKey;

    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/chat")
    public Map<String, String> chat(@org.springframework.web.bind.annotation.RequestBody  Map<String, String> request) throws IOException {
        String position = request.get("position");
        String company = request.get("company");
        String question = request.get("question");
        String prompt = "Job Position: " + position + "\nCompany: " + company + "\nQuestion: " + question + "\n\n";

        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        // Create JSON request body
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("model", "text-davinci-003");
        jsonMap.put("prompt", prompt);
        jsonMap.put("max_tokens", 150);

        String jsonRequestBody = objectMapper.writeValueAsString(jsonMap);
        RequestBody body = RequestBody.create(jsonRequestBody, JSON);

        Request httpRequest = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            JsonNode jsonResponse = objectMapper.readTree(responseBody);
            String completion = jsonResponse.get("choices").get(0).get("text").asText().trim();

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("response", completion);
            return responseMap;
        }
    }
}
