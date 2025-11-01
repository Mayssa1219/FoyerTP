package com.example.foyerTp.Services;


import com.example.foyerTp.Entity.Bloc;
import com.example.foyerTp.Repository.BlocRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IBlocServiceImpl implements IBlocService {

    @Autowired
    BlocRepository blocRepository;

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
}

