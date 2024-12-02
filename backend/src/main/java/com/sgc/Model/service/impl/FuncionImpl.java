package com.sgc.Model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.FuncionDao;
import com.sgc.Model.dto.FuncionDto;
import com.sgc.Model.entity.Funcion;
import com.sgc.Model.entity.Pelicula;
import com.sgc.Model.service.IFuncionService;
import com.sgc.Model.service.IPeliculaService;


@Service
public class FuncionImpl implements IFuncionService {

    @Autowired
    private FuncionDao funcionDao;
    @Autowired
    private IPeliculaService peliculaService;

    @Transactional
    @Override
    public Funcion save(FuncionDto funcionDto) {
        Pelicula pelicula = peliculaService.findById(funcionDto.getIdPelicula());
        Funcion funcion = Funcion.builder()
            .idfuncion(funcionDto.getIdfuncion())
            .capacidad(funcionDto.getCapacidad())
            .fecha(funcionDto.getFecha())
            .hora(funcionDto.getHora())
            .precio(funcionDto.getPrecio())
            .pelicula(pelicula)
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

    @Transactional
    @Override
    public List<Integer> getPeliculasIdsUnicos() {
        return funcionDao.findDistinctPeliculasIds();
    }
    
}
