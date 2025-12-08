package com.example.foyerTp.Services;


import com.example.foyerTp.Entity.Bloc;
import com.example.foyerTp.Entity.Chambre;
import com.example.foyerTp.Entity.Foyer;
import com.example.foyerTp.Entity.Universite;
import com.example.foyerTp.Repository.FoyerRepository;
import com.example.foyerTp.Repository.UniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoyerServiceImpl implements IFoyerService {

    @Autowired
    private FoyerRepository foyerRepository;
    @Autowired
    private UniversiteRepository universiteRepository;

    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer retrieveFoyer(Long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public void removeFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {

        //Vérifier si l'université existe
        Universite universite = universiteRepository
                .findById(idUniversite)
                .orElseThrow(() -> new RuntimeException
                        ("Université introuvable avec l'id : " + idUniversite));

        //Vérifier que le foyer ne possède pas déjà une université (optionnel selon cas métier)
        if (foyer.getUniversite() != null) {
            throw new RuntimeException("Ce foyer est déjà affecté à une université.");
        }

        //Vérifier que la liste des blocs n'est pas vide
        if (foyer.getBlocs() == null || foyer.getBlocs().isEmpty()) {
            throw new RuntimeException("Le foyer doit contenir au moins un bloc.");
        }

        //Affectation de l’université au foyer (relation ManyToOne)
        foyer.setUniversite(universite);

        //Affectation du foyer à l’université (relation OneToOne)
        universite.setFoyer(foyer);

        //Affectation bidirectionnelle des blocs  foyer
        for (Bloc bloc : foyer.getBlocs()) {
            bloc.setFoyer(foyer); // chaque bloc sait à quel foyer il appartient
        }

        /* if (foyer.getBlocs() != null)
            foyer.getBlocs().forEach(bloc -> bloc.setFoyer(foyer));
        return null; }
        */

        //Sauvegarder le foyer (cascade prendra en charge les blocs)
        Foyer saved = foyerRepository.save(foyer);

        return saved;
    }

}

