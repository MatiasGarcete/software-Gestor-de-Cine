package com.sgc.Model.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.RolDao;
import com.sgc.Model.dto.RolDto;
import com.sgc.Model.entity.Rol;
import com.sgc.Model.service.IRolService;

@Service
public class RolImpl implements IRolService{

    @Autowired
    private RolDao rolDao;

    @Override
    public boolean existsBy(Integer id){
        return rolDao.existsById(id);
    }

    @Transactional
    @Override
    public Rol save(RolDto rolDto) {
        //Setteamos todos los valores que vienen del UsuarioDto para generar un Objeto Usuario Entity
        Rol rol = Rol.builder()
            .idRol(rolDto.getIdRol())
            .nombreRol(rolDto.getNombreRol())
        .build();
        return rolDao.save(rol);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Rol> findAll(){
        return rolDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Rol findById(Integer id){
        return rolDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Rol rol) {
        rolDao.delete(rol);
    }

    @Transactional
    @Override
    public boolean existsByNombre(String nombreRol) {
        // Llama al método del DAO que hace la consulta
        return rolDao.existsByNombreRol(nombreRol);
    }
    
}
