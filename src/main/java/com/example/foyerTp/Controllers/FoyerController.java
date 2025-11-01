package com.example.foyerTp.Controllers;

import com.example.foyerTp.Entity.Foyer;
import com.example.foyerTp.Services.IFoyerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerController {
    @Autowired
    private IFoyerService foyerService;

    // GET /api/foyer
    @GetMapping
    public List<Foyer> getAllFoyer() {
        return foyerService.retrieveAllFoyers();
    }

    // GET /api/foyer/{id}
    @GetMapping("/{id}")
    public Foyer getFoyer(@PathVariable("id") Long id) {
        return foyerService.retrieveFoyer(id);
    }

    // POST /api/foyer
    @PostMapping
    public Foyer addupdateFoyer(@RequestBody Foyer f) {
        return foyerService.addFoyer(f);
    }

    // PUT /api/foyer/{id}
    @PutMapping("/{id}")
    public Foyer updateFoyer(@PathVariable("id") Long id, @RequestBody Foyer f) {
        f.setIdFoyer(id); // mettre à jour l’ID
        return foyerService.updateFoyer(f);
    }

    // DELETE /api/foyer/{id}
    @DeleteMapping("/{id}")
    public void deleteFoyer(@PathVariable("id") Long id) {
        foyerService.removeFoyer(id);
    }
}
