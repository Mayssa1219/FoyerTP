package com.example.foyerTp.Services;


import com.example.foyerTp.Entity.Foyer;
import com.example.foyerTp.Entity.Universite;
import com.example.foyerTp.Repository.FoyerRepository;
import com.example.foyerTp.Repository.UniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UniversiteServiceImpl implements IUniversiteService {

    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private FoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite retrieveUniversite(Long idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public void removeUniversite(Long idUniversite) {
        universiteRepository.deleteById(idUniversite);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {

        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer avec id " + idFoyer + " introuvable."));

        Universite universite = universiteRepository.findByNomUniversite(nomUniversite).orElseThrow(()->new RuntimeException("Universite avec nom " + nomUniversite + " introuvable."));
        if (universite == null) {
            throw new RuntimeException("Université '" + nomUniversite + "' introuvable.");
        }

        // Vérifier si l'université a déjà un foyer
        if (universite.getFoyer() != null) {
            throw new RuntimeException("Cette université a déjà un foyer affecté.");
        }

        // Vérifier si le foyer appartient déjà à une université
        if (foyer.getUniversite() != null) {
            throw new RuntimeException("Ce foyer est déjà affecté à une autre université.");
        }

        // Affectation bidirectionnelle
        universite.setFoyer(foyer);
        foyer.setUniversite(universite);

        return universiteRepository.save(universite);
    }



    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElseThrow(()-> new RuntimeException("Universite introuvable avec l'ID :"+ idUniversite));

        if (universite != null && universite.getFoyer() != null) {
            Foyer foyer = universite.getFoyer();

            // Suppression du lien bidirectionnel
            universite.setFoyer(null);
            foyer.setUniversite(null);

            universiteRepository.save(universite);
            return universite;
        }
        return null;
    }

}
