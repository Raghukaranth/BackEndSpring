package com.example.BackEndSpring.contorller;

import com.example.BackEndSpring.model.UserFeatureFlag;
import com.example.BackEndSpring.model.UserLoginDetails;
import com.example.BackEndSpring.service.FeatureFlagService;
import com.example.BackEndSpring.service.UserFeatureFlagService;
import com.example.BackEndSpring.service.UserLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-feature-flags")
public class UserFeatureFlagController {
    private final UserFeatureFlagService userFeatureFlagService;
    private final UserLoginService userLoginService;
    private final FeatureFlagService featureFlagService;

    public UserFeatureFlagController(UserFeatureFlagService userFeatureFlagService, UserLoginService userLoginService, FeatureFlagService featureFlagService) {
        this.userFeatureFlagService = userFeatureFlagService;
        this.userLoginService = userLoginService;
        this.featureFlagService = featureFlagService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<UserFeatureFlag>> getUserFeatureFlags(@PathVariable Long userId) {
        try {
            UserLoginDetails user = userLoginService.getUserFromId(userId);
            List<UserFeatureFlag> flags = userFeatureFlagService.getFeatureFlagsForUser(user);
            return ResponseEntity.ok(flags);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{userId}/{featureName}")
    public ResponseEntity<UserFeatureFlag> setUserFeatureFlag(
            @PathVariable Long userId,
            @PathVariable String featureName,
            @RequestParam boolean enabled) {
        try {
            UserLoginDetails user = userLoginService.getUserFromId(userId);
            UserFeatureFlag flag = userFeatureFlagService.createOrUpdateFeatureFlag(user, featureName, enabled);
            return ResponseEntity.status(HttpStatus.CREATED).body(flag);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{userId}/{featureName}")
    public ResponseEntity<Void> deleteUserFeatureFlag(@PathVariable Long userId, @PathVariable String featureName) {
        try {
            UserLoginDetails user = userLoginService.getUserFromId(userId);
            boolean deleted = userFeatureFlagService.deleteFeatureFlagForUser(user, featureName);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{userId}/{featureName}/enabled")
    public ResponseEntity<Boolean> isFeatureEnabledForUser(@PathVariable Long userId, @PathVariable String featureName) {
        try {
            UserLoginDetails user = userLoginService.getUserFromId(userId);
            boolean enabled = userFeatureFlagService.isFeatureEnabledForUser(user, featureName, featureFlagService);
            return ResponseEntity.ok(enabled);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
