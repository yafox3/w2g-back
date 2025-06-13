package ru.romanov.watchtogether.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/vk")
public class VkProxyController {

    private final RestTemplate restTemplate;

    public VkProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/proxy")
    public ResponseEntity<String> proxyVkRequest(
            @RequestParam Map<String, String> allParams) {

        String url = "https://api.vk.com/method/" + allParams.remove("method");
        StringBuilder query = new StringBuilder();

        allParams.forEach((k, v) ->
                query.append(k).append("=").append(v).append("&"));

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "WatchTogether/1.0");

        return restTemplate.exchange(
                url + "?" + query,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );
    }
}