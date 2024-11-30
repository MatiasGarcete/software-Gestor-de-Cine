package com.sgc.Model.service;


import com.sgc.Model.dto.RolDto;
import com.sgc.Model.entity.Rol;

public interface IRolService {
    Rol save(RolDto rolDto);
    Iterable<Rol> findAll();
    Rol findById(Integer id);
    void delete(Rol rol);
    boolean existsBy(Integer id);
    boolean existsByNombre(String nombre);
}
