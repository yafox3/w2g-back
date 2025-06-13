package ru.romanov.watchtogether.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vk")
public class VkProxyController {

    private final RestTemplate restTemplate;

    public VkProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/proxy")
    public ResponseEntity<String> proxyVkRequest(
            @RequestParam String method,
            @RequestParam Map<String, String> allParams) {

        String paramsString = allParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        String vkUrl = "https://api.vk.com/method/" + method + "?" + paramsString;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "WatchTogether/1.0");

        try {
            return restTemplate.exchange(
                    vkUrl,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("{\"error\":\"VK API unavailable\"}");
        }
    }
}