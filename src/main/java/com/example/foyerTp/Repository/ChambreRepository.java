package com.example.foyerTp.Repository;

import com.example.foyerTp.Entity.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long>{

}
