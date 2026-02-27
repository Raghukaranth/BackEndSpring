package com.example.BackEndSpring.service.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.Theater;
import com.example.BackEndSpring.repository.bookMyShow.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    public List<Theater> getAllTheater() {
        return theaterRepository.findAll();
    }

    public Optional<Theater> getAllTheaterById(Long theaterId) {
        return theaterRepository.findById(theaterId);
    }

    public Theater addTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    public Theater updateTheater(Long theaterId, Theater updateTheater) {
        return theaterRepository.findById(theaterId)
                .map(theater -> {
                    theater.setName(updateTheater.getName());
                    theater.setLocation(updateTheater.getLocation());
                    theater.setScreens(updateTheater.getScreens());
                    return theaterRepository.save(theater);
                }).orElseThrow(() -> new RuntimeException("Theater not found"));
    }

    public  void deleteTheater(Long theaterId) {
        theaterRepository.deleteById(theaterId);
    }
}
