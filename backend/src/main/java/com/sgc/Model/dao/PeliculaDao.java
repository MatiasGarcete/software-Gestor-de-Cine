package com.sgc.Model.dao;

import org.springframework.data.repository.CrudRepository;

import com.sgc.Model.entity.Pelicula;

//pelicula dao hereda los metodos/propiedades de la interfaz CrudRepository
public interface PeliculaDao extends CrudRepository<Pelicula, Integer>{
    
}
