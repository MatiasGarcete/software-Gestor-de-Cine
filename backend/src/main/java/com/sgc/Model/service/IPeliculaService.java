package com.sgc.Model.service;

import com.sgc.Model.dto.PeliculaDto;
import com.sgc.Model.entity.Pelicula;

public interface IPeliculaService {
    Pelicula save(PeliculaDto pelicula);
    Pelicula findById(Integer id);
    Iterable<Pelicula> findAll();
    void delete(Pelicula pelicula);
    boolean existsBy(Integer id);
}
