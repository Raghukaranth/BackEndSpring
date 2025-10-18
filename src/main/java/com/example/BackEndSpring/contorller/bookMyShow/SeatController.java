package com.example.BackEndSpring.contorller.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.Seat;
import com.example.BackEndSpring.service.bookMyShow.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mybms/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/screen/{seatId}")
    public ResponseEntity<List<Seat>> getSeatByScreen(@PathVariable Long seatId) {
        List<Seat> seats = seatService.getSeatsByScreenId(seatId);
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @PostMapping("/lock")
    public ResponseEntity<String> lockSeat(@RequestParam Long seatId, @RequestParam Long userId) {
        boolean locked = seatService.lockSeat(seatId, userId);

        if (locked)
            return new ResponseEntity<>("Seat Locked Successfully for the User: "+ userId , HttpStatus.OK);
        else
            return new ResponseEntity<>("Seat`cannot be locked. Already taken or Invalid.", HttpStatus.CONFLICT);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmSeats(@RequestBody List<Long>seatId, @RequestParam Long userId) {
        try {
            seatService.confirmSeats(seatId, userId);
            return new ResponseEntity<>("Seats confirmed Successfully user: " + userId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMappingat("/release")
    public ResponseEntity<String> releaseSeat(@RequestBody List<Long> seatId, @RequestParam Long userId) {
        seatService.releaseSeats(seatId, userId);
        return new ResponseEntity<>("Seats released successfully for the user: " + userId, HttpStatus.OK);
    }
}
