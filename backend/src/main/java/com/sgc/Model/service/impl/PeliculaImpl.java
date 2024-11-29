package com.sgc.Model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.PeliculaDao;
import com.sgc.Model.entity.Pelicula;
import com.sgc.Model.service.IPelicula;

@Service
public class PeliculaImpl implements IPelicula{
    @Autowired //nos deja instanciar la clase PeliculaDao
    private PeliculaDao peliculaDao;
    @Transactional
    @Override
    public Pelicula save(Pelicula pelicula) {
        return peliculaDao.save(pelicula);
    }

    @Transactional(readOnly = true)
    @Override
    public Pelicula findById(Integer id) {
        return peliculaDao.findById(id).orElse(null);
           }

    @Transactional
    @Override
    public Iterable<Pelicula> findAll() {
        return peliculaDao.findAll();
    }

    @Transactional
    @Override
    public void delete(Pelicula pelicula) {
        peliculaDao.delete(pelicula);
    }
    
}
