package com.example.BackEndSpring.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_feature_flags")
public class UserFeatureFlag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserLoginDetails user;

    @Column(name = "feature_name", nullable = false)
    private String featureName;

    @Column(nullable = false)
    private boolean enabled;
}
