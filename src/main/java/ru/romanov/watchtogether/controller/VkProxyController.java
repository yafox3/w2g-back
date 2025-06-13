package ru.romanov.watchtogether.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/vk")
public class VkProxyController {

    private final RestTemplate restTemplate;

    public VkProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/video.get")
    public ResponseEntity<String> getVideo(
            @RequestParam String videos,
            @RequestParam String v,
            HttpServletRequest request) {

        String token = request.getHeader("X-VK-Token");
        String url = String.format(
                "https://api.vk.com/method/video.get?videos=%s&v=%s&access_token=%s",
                videos, v, token
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "WatchTogether/1.0");
        headers.set("X-Real-IP", request.getRemoteAddr());

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_GATEWAY)
                    .body("{\"error\":\"VK API request failed\"}");
        }
    }
}