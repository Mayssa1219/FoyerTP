package com.example.foyerTp.Services;

import com.example.foyerTp.Entity.Bloc;
import com.example.foyerTp.Entity.Chambre;
import com.example.foyerTp.Entity.Etudiant;
import com.example.foyerTp.Entity.Reservation;
import com.example.foyerTp.Repository.BlocRepository;
import com.example.foyerTp.Repository.ChambreRepository;
import com.example.foyerTp.Repository.EtudiantRepository;
import com.example.foyerTp.Repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private BlocRepository blocRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public void removeReservation(String idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    // ===================== AJOUTER RESERVATION =====================
    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        Etudiant etudiant = etudiantRepository.findById(cinEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc introuvable"));

        // Trouver une chambre disponible
        Chambre chambreDisponible = bloc.getChambres().stream()
                .filter(ch -> ch.getReservations().size() < ch.getReservations().size())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aucune chambre disponible dans ce bloc"));

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setEstValide(true);
        reservation.setEtudiants(List.of(etudiant));
        reservation.setAnneeUniversitaire(new Date());

        // Générer l'idReservation : numChambre-nomBloc-année
        String year = new SimpleDateFormat("yyyy").format(new Date());
        reservation.setIdReservation(
                chambreDisponible.getNumeroChambre() + "-" + bloc.getNomBloc() + "-" + year
        );

        // Ajouter la réservation à la chambre
        chambreDisponible.getReservations().add(reservation);

        return reservationRepository.save(reservation);
    }

    // ===================== ANNULER RESERVATION =====================
    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        Etudiant etudiant = etudiantRepository.findById(cinEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        // Trouver la réservation valide de cet étudiant
        Reservation reservation = reservationRepository.findAll().stream()
                .filter(res -> res.isEstValide() && res.getEtudiants().contains(etudiant))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aucune réservation valide pour cet étudiant"));

        reservation.setEstValide(false);

        // Supprimer l'étudiant de la réservation
        reservation.getEtudiants().remove(etudiant);

        // Supprimer la réservation de la chambre associée
        reservation.getEtudiants().forEach(e -> e.getReservations().remove(reservation));

        return reservationRepository.save(reservation);
    }



}
