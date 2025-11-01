package com.example.foyerTp.Services;

import com.example.foyerTp.Entity.Reservation;
import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(String idReservation);
    Reservation addReservation(Reservation r);
    Reservation updateReservation(Reservation r);
    void removeReservation(String idReservation);
}

