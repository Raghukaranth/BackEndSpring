package com.example.BackEndSpring.repository.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {}

