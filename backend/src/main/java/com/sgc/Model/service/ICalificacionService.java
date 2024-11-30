package com.sgc.Model.service;

import com.sgc.Model.dto.CalificacionDto;
import com.sgc.Model.entity.Calificacion;

public interface ICalificacionService {
    Calificacion save(CalificacionDto calificacionDto);
    Calificacion findById(Integer id);
    Iterable<Calificacion> findAll();
    void delete(Calificacion calificacion);
    boolean existsBy(Integer id);
    boolean existsByCalificacion(String nombre);
}
