package com.example.foyerTp.Services;


import com.example.foyerTp.Entity.Bloc;
import com.example.foyerTp.Entity.Chambre;
import com.example.foyerTp.Repository.BlocRepository;
import com.example.foyerTp.Repository.ChambreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class IBlocServiceImpl implements IBlocService {
    private static final Logger log = LoggerFactory.getLogger(IChambreServiceImpl.class);

    @Autowired
    BlocRepository blocRepository;
    @Autowired
    ChambreRepository chambreRepository;

    @Override
    public List<Bloc> retrieveAllBlocs() {

        return blocRepository.findAll();
    }

    @Override
    public Bloc addBloc(Bloc bloc) {

        return blocRepository.save(bloc);
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {

        return blocRepository.save(bloc);
    }

    @Override
    public Bloc retrieveBloc(Long idBloc) {

        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public void removeBloc(Long idBloc) {

        blocRepository.deleteById(idBloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {

        // Récupérer le bloc
        Bloc bloc = blocRepository.findById(idBloc).orElseThrow(()->
                new RuntimeException("Bloc introuvable avec l'Id:"+idBloc));

        // Récupérer toutes les chambres à affecter sinon lancer une exception
        List<Chambre> chambres = chambreRepository.findAllByNumeroChambreIn(numChambre);

        //verifier la taille de deux tableaux est le mm
        if(chambres.size()!= numChambre.size()){
          throw new RuntimeException("Une ou plusieurs chambres sont introuvables");}

        //verifier la chambre est affectée à un autre bloc ou nn
        for (Chambre chambre : chambres) {
           if(chambre.getBloc()!=null && chambre.getBloc().getIdBloc()!=idBloc)
           {
               throw new RuntimeException("La chambre "+chambre.getNumeroChambre()+" est deja affectée à un autre bloc.");
           }
        }

        // Affectation bidirectionnelle
        for (Chambre chambre : chambres) {
            chambre.setBloc(bloc); // côté chambre
        }

       if(bloc.getChambres() == null) {
           bloc.setChambres(new ArrayList<Chambre>());
       }
       bloc.getChambres().addAll(chambres);

        // Sauvegarder les chambres et le bloc
        chambreRepository.saveAll(chambres);
        blocRepository.save(bloc);

        return bloc;
    }
    @Scheduled(cron = "0 * * * * *")
    public void listeChambreParBloc() {

        List<Bloc> blocs = blocRepository.findAll();

        if (blocs.isEmpty()) {
            log.info("Aucun bloc trouvé.");
            return;
        }

        for (Bloc bloc : blocs) {
            log.info("Bloc {} : Capcite{} : {} chambres",
                    bloc.getNomBloc(),
                    bloc.getCapaciteBloc(),
                    bloc.getChambres() != null ? bloc.getChambres().size() : 0);

            if (bloc.getChambres() != null && !bloc.getChambres().isEmpty()) {
                for (Chambre chambre : bloc.getChambres()) {
                    log.info("Chambre {} (Type : {})",
                            chambre.getNumeroChambre(),
                            chambre.getTypeC());
                }
            } else {
                log.info("Aucune chambre affectée à ce bloc.");
            }

        }
    }

}

