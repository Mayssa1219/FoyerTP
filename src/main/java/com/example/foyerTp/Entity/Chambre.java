package com.example.foyerTp.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="chambre")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Chambre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private long numeroChambre;

    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;

    @ManyToOne
    @JoinColumn(name = "bloc_id")
    private Bloc bloc;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservations;

}
