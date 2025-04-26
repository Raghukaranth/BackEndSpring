package com.example.BackEndSpring.contorller;

import com.example.BackEndSpring.model.FeatureFlag;
import com.example.BackEndSpring.model.UserLoginDetails;
import com.example.BackEndSpring.service.FeatureFlagService;
import com.example.BackEndSpring.service.UserLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/feature-flags")
public class FeatureFlagController {
    private final FeatureFlagService featureFlagService;

    public FeatureFlagController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    @GetMapping
    public ResponseEntity<Collection<FeatureFlag>> getAllFeatureFlags() {
        return ResponseEntity.ok(featureFlagService.getAllFeatureFlags());
    }

    @GetMapping("/{name}")
    public ResponseEntity<FeatureFlag> getFeatureFlag(@PathVariable String name) {
        FeatureFlag featureFlag = featureFlagService.getFeatureFlag(name);
        if (featureFlag == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(featureFlag);
    }

    @PostMapping
    public ResponseEntity<FeatureFlag> createFeatureFlag(@RequestBody FeatureFlag featureFlag) {
        FeatureFlag created = featureFlagService.createFeatureFlag(featureFlag);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{name}")
    public ResponseEntity<FeatureFlag> updateFeatureFlag(
            @PathVariable String name,
            @RequestBody FeatureFlag featureFlag) {
        featureFlag.setName(name); // Ensure the name is set correctly
        FeatureFlag updated = featureFlagService.updateFeatureFlag(name, featureFlag);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteFeatureFlag(@PathVariable String name) {
        boolean deleted = featureFlagService.deleteFeatureFlag(name);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // Simple endpoint to check if a feature is enabled
    @GetMapping("/{name}/enabled")
    public ResponseEntity<Boolean> isFeatureEnabled(@PathVariable String name) {
        FeatureFlag featureFlag = featureFlagService.getFeatureFlag(name);
        if (featureFlag == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(featureFlag.isEnabled());
    }
}
