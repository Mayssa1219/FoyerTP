package com.example.foyerTp.Services;

import com.example.foyerTp.Entity.Foyer;
import java.util.List;

public interface IFoyerService {
    List<Foyer> retrieveAllFoyers();
    Foyer retrieveFoyer(Long idFoyer);
    Foyer addFoyer(Foyer f);
    Foyer updateFoyer(Foyer f);
    void removeFoyer(Long idFoyer);
}
