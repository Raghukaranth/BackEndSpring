package com.example.BackEndSpring.repository.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {}
