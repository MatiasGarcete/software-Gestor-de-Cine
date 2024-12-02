package com.sgc.Model.service;

import java.util.List;

import com.sgc.Model.dto.FuncionDto;
import com.sgc.Model.entity.Funcion;

public interface IFuncionService {
    Funcion save(FuncionDto funcionDto);
    Funcion findById(Integer id);
    Iterable<Funcion> findAll();
    void delete(Funcion funcion);
    boolean existsBy(Integer id);
    List<Integer> getPeliculasIdsUnicos();
} 