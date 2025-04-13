package com.example.BackEndSpring.model;

import java.util.HashMap;
import java.util.Map;

public class FeatureFlag {
    private String name;
    private boolean enabled;
    private Map<String, Object> properties = new HashMap<>();

    public FeatureFlag(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
