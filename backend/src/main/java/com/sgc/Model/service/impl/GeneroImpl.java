package com.sgc.Model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.GeneroDao;
import com.sgc.Model.dto.GeneroDto;
import com.sgc.Model.entity.Genero;
import com.sgc.Model.service.IGeneroService;

@Service
public class GeneroImpl implements IGeneroService{
    @Autowired
    private GeneroDao generoDao;

    @Transactional
    @Override
    public Genero save(GeneroDto generoDto) {
        Genero genero = Genero.builder()
            .idgenero(generoDto.getIdgenero())
            .genero(generoDto.getGenero())
        .build();

        return generoDao.save(genero);
    }

    @Transactional(readOnly = true)
    @Override
    public Genero findById(Integer id) {
        return generoDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Genero> findAll() {
        return generoDao.findAll();
    }

    @Transactional
    @Override
    public void delete(Genero genero) {
        generoDao.delete(genero);
    }

    @Override
    public boolean existsBy(Integer id) {
        return generoDao.existsById(id);
    }

    @Transactional
    @Override
    public boolean existsByGenero(String genero){
        // Llama al método del DAO que hace la consulta
        return generoDao.existsByGenero(genero);
    }
    
}
