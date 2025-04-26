package com.example.BackEndSpring.repository;

import com.example.BackEndSpring.model.UserFeatureFlag;
import com.example.BackEndSpring.model.UserLoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFeatureFlagRepository extends JpaRepository<UserFeatureFlag, Long> {
    List<UserFeatureFlag> findByUser(UserLoginDetails user);

    Optional<UserFeatureFlag> findByUserAndFeatureName(UserLoginDetails user, String featureName);

    void deleteByUserAndFeatureName(UserLoginDetails user, String featureName);
}
