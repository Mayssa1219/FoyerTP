package com.example.foyerTp.Repository;

import com.example.foyerTp.Entity.Bloc;
import com.example.foyerTp.Entity.Chambre;
import com.example.foyerTp.Entity.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long>{
List<Chambre> findAllByNumeroChambreIn(List<Long> numeroChambre);

@Query("SELECT c FROM Chambre c " +
        "WHERE c.bloc.foyer.universite.nomUniversite = :nomUniversite")
    List<Chambre> findChambresByNomUniversite(@Param("nomUniversite") String nomUniversite);

@Query("SELECT c FROM Chambre c " +
            "WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> findByBlocAndType(@Param("idBloc") long idBloc,
                                    @Param("typeC") TypeChambre typeC);
    @Query("SELECT c.typeC as type, COUNT(c) as count FROM Chambre c GROUP BY c.typeC")
    List<Object[]> countChambresByType();



}
