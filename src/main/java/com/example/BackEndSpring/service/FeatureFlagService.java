package com.example.BackEndSpring.service;

import com.example.BackEndSpring.model.FeatureFlag;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FeatureFlagService {
    private final Map<String, FeatureFlag> featureFlags = new ConcurrentHashMap<>();

    public FeatureFlagService() {
        // Initialize with some default feature flags
        featureFlags.put("newFeature", new FeatureFlag("newFeature", false));
        featureFlags.put("betaFeature", new FeatureFlag("betaFeature", false));
    }

    public Collection<FeatureFlag> getAllFeatureFlags() {
        return featureFlags.values();
    }

    public FeatureFlag getFeatureFlag(String name) {
        return featureFlags.get(name);
    }

    public boolean isFeatureEnabled(String name) {
        FeatureFlag flag = featureFlags.get(name);
        return flag != null && flag.isEnabled();
    }

    public FeatureFlag updateFeatureFlag(String name, FeatureFlag updatedFlag) {
        if (featureFlags.containsKey(name)) {
            featureFlags.put(name, updatedFlag);
            return updatedFlag;
        }
        return null;
    }

    public FeatureFlag createFeatureFlag(FeatureFlag featureFlag) {
        featureFlags.put(featureFlag.getName(), featureFlag);
        return featureFlag;
    }

    public boolean deleteFeatureFlag(String name) {
        return featureFlags.remove(name) != null;
    }
}
