package com.example.foyerTp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="reservation")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    private String idReservation;

    @Temporal(TemporalType.DATE)
    private Date anneeUniversitaire;

    private boolean estValide;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Etudiant> etudiants;

}
