package com.example.BackEndSpring.repository.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findUserById(Long userId);
}
