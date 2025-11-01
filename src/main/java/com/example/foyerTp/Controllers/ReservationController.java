package com.example.foyerTp.Controllers;

import com.example.foyerTp.Entity.Reservation;
import com.example.foyerTp.Services.IReservationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private IReservationService reservationService;

    // GET /api/reservations
    @GetMapping
    public List<Reservation> getAllReservation() {
        return reservationService.retrieveAllReservations();
    }

    // GET /api/reservations/{id}
    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") String id) {
        return reservationService.retrieveReservation(id);
    }

    // POST /api/reservations
    @PostMapping
    public Reservation addReservation(@RequestBody Reservation r) {
        return reservationService.addReservation(r);
    }

    // PUT /api/reservations/{id}
    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id") String id, @RequestBody Reservation r) {
        r.setIdReservation(id); // mettre à jour l’ID
        return reservationService.updateReservation(r);
    }

    // DELETE /api/reservations/{id}
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") String id) {
        reservationService.removeReservation(id);
    }
}
