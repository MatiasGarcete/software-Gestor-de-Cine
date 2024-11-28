package com.sgc.Model.service;


import com.sgc.Model.entity.Rol;

public interface IRol {
    Rol save(Rol rol);
    Iterable<Rol> findAll();
    Rol findById(Integer id);
    void delete(Rol rol);
}
