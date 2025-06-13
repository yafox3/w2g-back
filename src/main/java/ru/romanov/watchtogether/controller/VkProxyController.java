package ru.romanov.watchtogether.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/vk")
public class VkProxyController {

    private final RestTemplate restTemplate;

    // Явно указываем, что нужно внедрить бин RestTemplate
    public VkProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/proxy")
    public ResponseEntity<String> proxyVkRequest(
            @RequestParam Map<String, String> allParams) {

        String method = allParams.remove("method");
        String url = "https://api.vk.com/method/" + method;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "WatchTogether/1.0");

        return restTemplate.exchange(
                url + "?" + buildQueryString(allParams),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );
    }

    private String buildQueryString(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        params.forEach((k, v) ->
                query.append(k).append("=").append(v).append("&"));
        return query.toString();
    }
}