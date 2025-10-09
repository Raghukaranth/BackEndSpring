    package com.example.BackEndSpring.service.bookMyShow;

    import com.example.BackEndSpring.model.bookMyShow.Seat;
    import com.example.BackEndSpring.model.bookMyShow.SeatStatus;
    import com.example.BackEndSpring.repository.bookMyShow.SeatRepository;
    import jakarta.transaction.Transactional;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class SeatService {
        @Autowired
        private SeatRepository seatRepository;

        public List<Seat> getSeatsByScreenId(Long screenId) {
            return seatRepository.findByScreenId(screenId);
        }

        @Transactional
        public boolean lockSeat(Long seatId, Long userId) {
            Optional<Seat> seatOpt = seatRepository.findById(seatId);
            if(seatOpt.isPresent()) {
                Seat seat = seatOpt.get();
                if(seat.getStatus() == SeatStatus.AVAILABLE) {
                    seat.setStatus(SeatStatus.LOCKED);
                    seat.setLockedByUserId(userId);
                    seatRepository.save(seat);
                    return true;
                }
            }
            return false;
        }

        @Transactional
        public void confirmSeats(List<Long> seatIds, Long userId) {
            List<Seat> seats = seatRepository.findAllById(seatIds);
            for (Seat seat : seats) {
                if (seat.getStatus() == SeatStatus.LOCKED && seat.getLockedByUserId().equals(userId)) {
                    seat.setStatus(SeatStatus.BOOKED);
                    seat.setLockedByUserId(null);
                    seatRepository.save(seat);
                } else {
                    throw new RuntimeException("Seat not locked by user or unavailable");
                }
            }
        }

        @Transactional
        public void releaseSeats(List<Long> seatIds, Long userId) {
            List<Seat> seats = seatRepository.findAllById(seatIds);
            for (Seat seat : seats) {
                if (seat.getStatus() == SeatStatus.LOCKED && seat.getLockedByUserId().equals(userId)) {
                    seat.setStatus(SeatStatus.AVAILABLE);
                    seat.setLockedByUserId(null);
                    seatRepository.save(seat);
                }
            }
        }
    }
