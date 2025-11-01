package com.example.foyerTp.Controllers;

import com.example.foyerTp.Entity.Bloc;
import com.example.foyerTp.Entity.Etudiant;
import com.example.foyerTp.Services.IBlocService;
import com.example.foyerTp.Services.IEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/blocs")
public class BlocController {
    @Autowired
    private IBlocService blocService;

    // GET /api/etudiants
    @GetMapping
    public List<Bloc> getAllBlocs() {
        return blocService.retrieveAllBlocs();
    }

    // GET /api/etudiants/{id}
    @GetMapping("/{id}")
    public Bloc getBloc(@PathVariable("id") Long id) {
        return blocService.retrieveBloc(id);
    }

    // POST /api/etudiants
    @PostMapping
    public Bloc addBloc(@RequestBody Bloc b) {
        return blocService.addBloc(b);
    }

    // PUT /api/etudiants/{id}
    @PutMapping("/{id}")
    public Bloc updateBloc(@PathVariable("idBloc") Long id, @RequestBody Bloc b) {
        b.setIdBloc(id); // mettre à jour l’ID
        return blocService.updateBloc(b);
    }

    // DELETE /api/etudiants/{id}
    @DeleteMapping("/{id}")
    public void deleteBloc(@PathVariable("id") Long id) {
        blocService.removeBloc(id);
    }
}
