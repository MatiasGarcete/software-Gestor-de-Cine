package com.sgc.Model.service;

import com.sgc.Model.entity.Pelicula;

public interface IPelicula {
    Pelicula save(Pelicula pelicula);
    Pelicula findById(Integer id);
    Iterable<Pelicula> findAll();
    void delete(Pelicula pelicula);
}
