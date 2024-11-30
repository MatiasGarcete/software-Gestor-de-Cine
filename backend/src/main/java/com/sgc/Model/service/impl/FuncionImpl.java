package com.sgc.Model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.FuncionDao;
import com.sgc.Model.dto.FuncionDto;
import com.sgc.Model.entity.Funcion;
import com.sgc.Model.service.IFuncionService;


@Service
public class FuncionImpl implements IFuncionService {

    @Autowired
    private FuncionDao funcionDao;

    @Transactional
    @Override
    public Funcion save(FuncionDto funcionDto) {
        Funcion funcion = Funcion.builder()
            .idfuncion(funcionDto.getIdfuncion())
            .capacidad(funcionDto.getCapacidad())
            .fecha(funcionDto.getFecha())
            .hora(funcionDto.getHora())
            .precio(funcionDto.getPrecio())
        .build();

        return funcionDao.save(funcion);
    }

    @Transactional(readOnly = true)
    @Override
    public Funcion findById(Integer id) {
        return funcionDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Funcion> findAll() {
        return funcionDao.findAll();
    }

    @Transactional
    @Override
    public void delete(Funcion funcion) {
        funcionDao.delete(funcion);
    }

    @Transactional
    @Override
    public boolean existsBy(Integer id) {
        return funcionDao.existsById(id);
    }
    
}
