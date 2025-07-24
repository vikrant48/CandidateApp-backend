package com.userauth.controller;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ai")
public class ChatBotController {

    @Value("${gemini.api.key}")
    private String GEMINI_API_KEY;

    @GetMapping("/check-key")
    public ResponseEntity<String> checkApiKey() {
        if (GEMINI_API_KEY == null || GEMINI_API_KEY.isEmpty()) {
            return ResponseEntity.status(500).body("Gemini API key is not set.");
        }
        return ResponseEntity.ok("Gemini API key is set.");
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody Map<String, String> request) throws IOException {
        String userMessage = request.get("message");

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.get("application/json");
        String json = String.format("""
            {
              "contents": [
                {
                  "parts": [
                    { "text": "%s" }
                  ]
                }
              ]
            }
            """, userMessage.replace("\"", "\\\""));

        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, json);

        Request req = new Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1/models/gemini-2.5-pro:generateContent?key=" + GEMINI_API_KEY)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(req).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Gemini Response: " + response.body().string());
                return ResponseEntity.ok(response.body().string());
            } else {
                return ResponseEntity.status(response.code())
                        .body(response.body() != null ? response.body().string() : "Unknown error occurred");
            }
        }
    }
}
