package com.example.foyerTp.Services;


import com.example.foyerTp.Entity.Etudiant;
import java.util.List;

public interface IEtudiantService {
    List<Etudiant> retrieveAllEtudiants();
    Etudiant retrieveEtudiant(Long idEtudiant);
    Etudiant addEtudiant(Etudiant e);
    Etudiant updateEtudiant(Etudiant e);
    void removeEtudiant(Long idEtudiant);
}
