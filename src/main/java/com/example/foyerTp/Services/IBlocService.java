package com.example.foyerTp.Services;

import com.example.foyerTp.Entity.Bloc;

import java.util.List;

public interface IBlocService {
    List<Bloc> retrieveAllBlocs();
    Bloc addBloc(Bloc bloc);
    Bloc updateBloc(Bloc bloc);
    Bloc retrieveBloc(Long idBloc);
    void removeBloc(Long idBloc);
}