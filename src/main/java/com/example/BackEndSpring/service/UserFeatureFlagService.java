package com.example.BackEndSpring.service;

import com.example.BackEndSpring.model.UserFeatureFlag;
import com.example.BackEndSpring.model.UserLoginDetails;
import com.example.BackEndSpring.repository.UserFeatureFlagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFeatureFlagService {
    private final UserFeatureFlagRepository userFeatureFlagRepository;

    public UserFeatureFlagService(UserFeatureFlagRepository userFeatureFlagRepository) {
        this.userFeatureFlagRepository = userFeatureFlagRepository;
    }

    public List<UserFeatureFlag> getFeatureFlagsForUser(UserLoginDetails user) {
        return userFeatureFlagRepository.findByUser(user);
    }

    public Optional<UserFeatureFlag> getFeatureFlagForUser(UserLoginDetails user, String featureName) {
        return userFeatureFlagRepository.findByUserAndFeatureName(user, featureName);
    }

    public UserFeatureFlag createOrUpdateFeatureFlag(UserLoginDetails user, String featureName, boolean enabled) {
        Optional<UserFeatureFlag> existingFlag = userFeatureFlagRepository.findByUserAndFeatureName(user, featureName);
        UserFeatureFlag flag = existingFlag.orElseGet(UserFeatureFlag::new);
        flag.setUser(user);
        flag.setFeatureName(featureName);
        flag.setEnabled(enabled);
        return userFeatureFlagRepository.save(flag);
    }

    public boolean deleteFeatureFlagForUser(UserLoginDetails user, String featureName) {
        Optional<UserFeatureFlag> existingFlag = userFeatureFlagRepository.findByUserAndFeatureName(user, featureName);
        if (existingFlag.isPresent()) {
            userFeatureFlagRepository.delete(existingFlag.get());
            return true;
        }
        return false;
    }

    /**
     * Check if feature is enabled for user, fallback to global flag if user-specific flag not found
     */
    public boolean isFeatureEnabledForUser(UserLoginDetails user, String featureName, FeatureFlagService featureFlagService) {
        Optional<UserFeatureFlag> userFlag = getFeatureFlagForUser(user, featureName);
        return userFlag.map(UserFeatureFlag::isEnabled).orElseGet(() -> featureFlagService.isFeatureEnabled(featureName));
        // fallback to global feature flag
    }
}
