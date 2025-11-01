package com.example.foyerTp.Controllers;


import com.example.foyerTp.Entity.Etudiant;
import com.example.foyerTp.Services.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")  // endpoint de base
public class EtudiantController {

    @Autowired
    private IEtudiantService etudiantService;

    // GET /api/etudiants
    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }

    // GET /api/etudiants/{id}
    @GetMapping("/{id}")
    public Etudiant getEtudiant(@PathVariable("id") Long id) {
        return etudiantService.retrieveEtudiant(id);
    }

    // POST /api/etudiants
    @PostMapping
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        return etudiantService.addEtudiant(e);
    }

    // PUT /api/etudiants/{id}
    @PutMapping("/{id}")
    public Etudiant updateEtudiant(@PathVariable("id") Long id, @RequestBody Etudiant e) {
        e.setIdEtudiant(id); // mettre à jour l’ID
        return etudiantService.updateEtudiant(e);
    }

    // DELETE /api/etudiants/{id}
    @DeleteMapping("/{id}")
    public void deleteEtudiant(@PathVariable("id") Long id) {
        etudiantService.removeEtudiant(id);
    }
}
