package com.example.foyerTp.Services;


import com.example.foyerTp.Entity.Chambre;
import com.example.foyerTp.Entity.TypeChambre;
import com.example.foyerTp.Repository.ChambreRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class IChambreServiceImpl implements IChambreService {
    private static final Logger log = LoggerFactory.getLogger(IChambreServiceImpl.class);

    @Autowired
    ChambreRepository chambreRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).orElse(null);
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }

    @Override
    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

   /* @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        return chambreRepository.findAll().stream()
                .filter(c -> c.getBloc() != null
                        && c.getBloc().getFoyer() != null
                        && c.getBloc().getFoyer().getUniversite() != null
                        && c.getBloc().getFoyer().getUniversite().getNomUniversite().equalsIgnoreCase(nomUniversite))
                .toList();
    }*/
   @Override
   public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
       return chambreRepository.findChambresByNomUniversite(nomUniversite);
   }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        return chambreRepository.findAll().stream()
                .filter(c -> c.getBloc() != null
                        && c.getBloc().getFoyer() != null
                        && c.getBloc().getFoyer().getUniversite() != null
                        && c.getBloc().getFoyer().getUniversite().getNomUniversite().equalsIgnoreCase(nomUniversite)
                        && c.getTypeC() == type
                        && (c.getReservations() == null || c.getReservations().isEmpty()))
                .toList();
    }

    @Override
    public void afficherChambresNonReservees() {
        List<Chambre> chambresNonReservees = chambreRepository.findAll().stream()
                .filter(c -> c.getReservations() == null || c.getReservations().isEmpty())
                .toList();

        chambresNonReservees.forEach(c ->
                System.out.println("Chambre n°" + c.getNumeroChambre() + ", Type: " + c.getTypeC()));
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepository.findByBlocAndType(idBloc, typeC);
    }

    @Scheduled(cron = "0/15 * * * * *")
    public void pourcentagesChambreParTypeChambre() {
        List<Chambre> chambres = chambreRepository.findAll();
        int totalChambres = chambres.size();
        log.info("Total des chambres : {}", totalChambres);

        if (totalChambres > 0) {
            Map<String, Integer> countByType = new HashMap<>();

            // Compter le nombre de chambres par type
            for (Chambre chambre : chambres) {
                String type = String.valueOf(chambre.getTypeC());
                countByType.put(type, countByType.getOrDefault(type, 0) + 1);
            }

            // Calculer et afficher les pourcentages
            for (Map.Entry<String, Integer> entry : countByType.entrySet()) {
                String type = entry.getKey();
                int count = entry.getValue();
                double pourcentage = (count * 100.0) / totalChambres;
                log.info("Type {} : {} chambres ({:.2f}%)", type, count, pourcentage);
            }
        } else {
            log.info("Aucune chambre trouvée.");
        }
}

}
