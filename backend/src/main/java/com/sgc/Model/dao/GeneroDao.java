package com.sgc.Model.dao;

import org.springframework.data.repository.CrudRepository;

import com.sgc.Model.entity.Genero;

public interface GeneroDao extends CrudRepository<Genero, Integer>{
    // Método personalizado para verificar si el nombre existe
    boolean existsByGenero(String genero);
    
}


