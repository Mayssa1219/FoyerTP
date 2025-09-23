package com.example.foyerTp.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="chambre")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private long numeroChambre;
    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;
    @ManyToOne
    @JoinColumn(name = "bloc_id")
    private Bloc bloc;
}
