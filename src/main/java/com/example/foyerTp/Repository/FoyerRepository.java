package com.example.foyerTp.Repository;

import com.example.foyerTp.Entity.Foyer;
import com.example.foyerTp.Entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer,Long> {

}
