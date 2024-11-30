package com.sgc.Model.dao;

import org.springframework.data.repository.CrudRepository;

import com.sgc.Model.entity.Rol;

public interface RolDao extends CrudRepository<Rol, Integer>{
    // Método personalizado para verificar si el nombre existe
    boolean existsByNombreRol(String nombreRol);
}
