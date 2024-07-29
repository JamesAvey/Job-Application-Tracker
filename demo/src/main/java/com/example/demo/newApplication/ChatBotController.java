package com.example.demo.newApplication;

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

    @PostMapping("/chat")
    public Map<String, String> chat(@org.springframework.web.bind.annotation.RequestBody Map<String, String> request) throws IOException {
        String position = request.get("position");
        String company = request.get("company");
        String question = request.get("question");
        String prompt = "Job Position: " + position + "\nCompany: " + company + "\nQuestion: " + question + "\n\n";

        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String jsonRequestBody = "{\"prompt\":\"" + prompt + "\",\"max_tokens\":150}";

        // Use okhttp3.RequestBody here
        okhttp3.RequestBody body = okhttp3.RequestBody.create(jsonRequestBody, JSON);

        // Build the request using OkHttp's Request class
        Request httpRequest = new Request.Builder()
                .url("https://api.openai.com/v1/engines/davinci-codex/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        // Execute the request and handle the response
        try (Response response = client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseBody = response.body().string();
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("response", responseBody);
            return responseMap;
        }
    }
}
