package com.example.foyerTp.Services;

import com.example.foyerTp.Entity.Bloc;
import com.example.foyerTp.Entity.Chambre;
import com.example.foyerTp.Entity.TypeChambre;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface IChambreService {

    List<Chambre> retrieveAllChambres();

    Chambre retrieveChambre(Long chambreId);

    Chambre addChambre(Chambre c);

    void removeChambre(Long chambreId);

    Chambre modifyChambre(Chambre chambre);
    List<Chambre> getChambresParNomUniversite( String nomUniversite) ;
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) ;
    @Scheduled(cron = "0 0 0 * * *") // chaque jour à minuit
    void afficherChambresNonReservees();

     List<Chambre> getChambresParBlocEtType (long idBloc, TypeChambre typeC) ;


}
