package org.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CrptApi {
    private static final String API_URL = "https://ismp.crpt.ru/api/v3/lk/documents/create";
    private static final String TOKEN_URL = "https://ismp.crpt.ru/api/v3/auth/token"; // Пример URL для получения токена
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final HttpClient httpClient;
    private final ScheduledExecutorService scheduler;
    private final Semaphore semaphore;
    private String authToken;  // Поле для хранения токена аутентификации

    public CrptApi(TimeUnit timeUnit, int requestLimit, String username, String password) {
        this.httpClient = HttpClient.newHttpClient();
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.semaphore = new Semaphore(requestLimit);

        // Получаем токен при инициализации
        this.authToken = authenticate(username, password);

        // Schedule a task to replenish permits every timeUnit
        scheduler.scheduleAtFixedRate(this::replenishPermits, 0, 1, timeUnit);
    }

    // Метод для получения токена
    private String authenticate(String username, String password) {
        String token = null;
        try {
            String authRequestBody = objectMapper.writeValueAsString(new AuthRequest(username, password));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TOKEN_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(authRequestBody))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                AuthResponse authResponse = objectMapper.readValue(response.body(), AuthResponse.class);
                token = authResponse.getToken();
            } else {
                System.err.println("Failed to get token. Status code: " + response.statusCode());
                System.err.println("Response body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return token;
    }

    public void createIntroduceGoodsDocument(IntroduceGoodsDocument document, String signature) {
        try {
            semaphore.acquire(); // Acquire permit, block if none available

            String requestBody = buildRequestBody(document, signature);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + authToken)  // Используем токен аутентификации
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            handleResponse(response);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        } finally {
            semaphore.release(); // Release permit after execution
        }
    }

    private void replenishPermits() {
        semaphore.release(semaphore.availablePermits()); // Replenish all permits
    }

    private String buildRequestBody(IntroduceGoodsDocument document, String signature) throws IOException {
        // Attach signature to the document if needed
        // Currently, signature is not used in the JSON, but you can include it if necessary.
        return objectMapper.writeValueAsString(document);
    }

    private void handleResponse(HttpResponse<String> response) {
        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Document creation successful.");
        } else {
            System.err.println("Failed to create document. Status code: " + statusCode);
            System.err.println("Response body: " + responseBody);
        }
    }
}
