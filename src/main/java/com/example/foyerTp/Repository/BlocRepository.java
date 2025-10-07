package com.example.foyerTp.Repository;

import com.example.foyerTp.Entity.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {
}
