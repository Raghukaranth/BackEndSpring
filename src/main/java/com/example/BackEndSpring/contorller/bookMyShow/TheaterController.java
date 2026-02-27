package com.example.BackEndSpring.contorller.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.Theater;
import com.example.BackEndSpring.service.bookMyShow.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mybms/theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters() {
        List<Theater> theaters = theaterService.getAllTheater();
        return new ResponseEntity<>(theaters, HttpStatus.OK);
    }

    @GetMapping("/{theaterId}")
    public ResponseEntity<Theater> getTheatersById(@PathVariable Long theaterId) {
        Optional<Theater> theater = theaterService.getAllTheaterById(theaterId);
        return theater.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Theater> addTheater(@RequestBody Theater theater) {
        Theater newTheater = theaterService.addTheater(theater);
        return new ResponseEntity<>(newTheater, HttpStatus.CREATED);
    }

    @PutMapping("/{theaterId}")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long theaterId, @RequestBody Theater updatedTheater) {
        try {
            Theater theater = theaterService.updateTheater(theaterId, updatedTheater);
            return new ResponseEntity<>(theater, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{theaterId}")
    public ResponseEntity<String> deleteTheater(@PathVariable Long theaterId) {
        theaterService.deleteTheater(theaterId);
        return new ResponseEntity<>("Theater deleted successfully", HttpStatus.OK);
    }
}
