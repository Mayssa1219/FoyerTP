package com.example.foyerTp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="etudiant")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long idEtudiant;
    String nomEt;
    String prenomEt;
    private Long cin;
    private String ecole;

    @JsonFormat(pattern = "yy-mm-dd")
    private Date dateNaissance;

    @ManyToMany(mappedBy = "etudiants", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

}
