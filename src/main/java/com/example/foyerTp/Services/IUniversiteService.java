package com.example.foyerTp.Services;

import com.example.foyerTp.Entity.Universite;
import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversites();
    Universite retrieveUniversite(Long idUniversite);
    Universite addUniversite(Universite u);
    Universite updateUniversite(Universite u);
    void removeUniversite(Long idUniversite);
    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);
    Universite desaffecterFoyerAUniversite(long idUniversite);

}
