package com.example.BackEndSpring.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class FeatureFlag {
    private String name;
    private boolean enabled;
    private Map<String, Object> properties = new HashMap<>();

    public FeatureFlag(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

}
