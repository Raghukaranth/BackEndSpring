package com.example.BackEndSpring.contorller;

import com.example.BackEndSpring.service.FeatureFlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/example")
public class ExampleController {
    private final FeatureFlagService featureFlagService;

    @Autowired
    public ExampleController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    @GetMapping("/feature")
    public String getFeature() {
        if (featureFlagService.isFeatureEnabled("newFeature")) {
            return "New feature is enabled!";
        } else {
            return "New feature is disabled.";
        }
    }
}
