package com.sgc.Model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sgc.Model.entity.Funcion;

public interface FuncionDao extends CrudRepository<Funcion, Integer> {
    // Usar DISTINCT para evitar duplicados
    @Query("SELECT DISTINCT f.pelicula.idPelicula FROM Funcion f")
    List<Integer> findDistinctPeliculasIds();
}
