package ru.romanov.watchtogether.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
<<<<<<< HEAD
=======

import java.util.Map;
import java.util.stream.Collectors;
>>>>>>> 77047af88415e52ad514f2f0914709e7f67bce7e

@RestController
@RequestMapping("/api/vk")
public class VkProxyController {

    private final RestTemplate restTemplate;

    public VkProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

<<<<<<< HEAD
    @GetMapping("/video.get")
    public ResponseEntity<String> getVideo(
            @RequestParam String videos,
            @RequestParam String v,
            @RequestParam String access_token) {

        String url = String.format(
                "https://api.vk.com/method/video.get?videos=%s&v=%s&access_token=%s",
                videos, v, access_token
        );
=======
    @GetMapping("/proxy")
    public ResponseEntity<String> proxyVkRequest(
            @RequestParam String method,
            @RequestParam Map<String, String> allParams) {

        String paramsString = allParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        String vkUrl = "https://api.vk.com/method/" + method + "?" + paramsString;
>>>>>>> 77047af88415e52ad514f2f0914709e7f67bce7e

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "WatchTogether/1.0");

        try {
            return restTemplate.exchange(
<<<<<<< HEAD
                    url,
=======
                    vkUrl,
>>>>>>> 77047af88415e52ad514f2f0914709e7f67bce7e
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
        } catch (Exception e) {
<<<<<<< HEAD
            return ResponseEntity
                    .status(HttpStatus.BAD_GATEWAY)
                    .body("{\"error\":\"VK API request failed\"}");
=======
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("{\"error\":\"VK API unavailable\"}");
>>>>>>> 77047af88415e52ad514f2f0914709e7f67bce7e
        }
    }
}