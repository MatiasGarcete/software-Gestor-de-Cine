package com.sgc.Model.dao;

import org.springframework.data.repository.CrudRepository;

import com.sgc.Model.entity.Calificacion;

public interface CalificacionDao extends CrudRepository<Calificacion, Integer>{
    // Método personalizado para verificar si el nombre existe
    boolean existsByCalificacion(String nombre);
    
}
