package com.example.foyerTp.Controllers;

import com.example.foyerTp.Entity.Universite;
import com.example.foyerTp.Services.IUniversiteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universites")  // base URL pour les universités
public class UniversiteController {

    @Autowired
    private IUniversiteService universiteService;

    // GET /api/universites → récupère toutes les universités
    @GetMapping
    public List<Universite> getAllUniversites() {
        return universiteService.retrieveAllUniversites();
    }

    // GET /api/universites/{id} → récupère une université par ID
    @GetMapping("/{id}")
    public Universite getUniversite(@PathVariable("id") Long id) {
        return universiteService.retrieveUniversite(id);
    }

    // POST /api/universites → ajoute une nouvelle université
    @PostMapping
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteService.addUniversite(u);
    }

    // PUT /api/universites/{id} → met à jour une université
    @PutMapping("/{id}")
    public Universite updateUniversite(@PathVariable("id") Long id, @RequestBody Universite u) {
        u.setIdUniversite(id); // Assure que l’ID est correct
        return universiteService.updateUniversite(u);
    }

    // DELETE /api/universites/{id} → supprime une université
    @DeleteMapping("/{id}")
    public void deleteUniversite(@PathVariable("id") Long id) {
        universiteService.removeUniversite(id);
    }
}
