package com.example.BackEndSpring.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/url")
public class UrlController {
    @GetMapping(value = "/google")
    public String getGoogleWepPage() {
        String uri = "https://www.google.com";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    @GetMapping(value = "/perplexity")
        public String getPerplexityWepPage() {
            String uri = "https://www.perplexity.com";
            RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
        }
}

