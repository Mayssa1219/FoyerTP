package com.example.foyerTp.Services;

import com.example.foyerTp.Entity.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(String idReservation);
    Reservation addReservation(Reservation r);
    Reservation updateReservation(Reservation r);
    void removeReservation(String idReservation);
    Reservation ajouterReservation (long idBloc, long cinEtudiant);
    Reservation annulerReservation (long cinEtudiant) ;

}

