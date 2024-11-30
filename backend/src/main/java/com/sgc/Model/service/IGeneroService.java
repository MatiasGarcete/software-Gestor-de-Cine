package com.sgc.Model.service;

import com.sgc.Model.dto.GeneroDto;
import com.sgc.Model.entity.Genero;

public interface IGeneroService {
    Genero save(GeneroDto generoDto);
    Genero findById(Integer id);
    Iterable<Genero> findAll();
    void delete(Genero genero);
    boolean existsBy(Integer id);
    boolean existsByGenero(String genero);
}
