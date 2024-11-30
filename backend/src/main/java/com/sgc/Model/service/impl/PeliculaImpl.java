package com.sgc.Model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.PeliculaDao;
import com.sgc.Model.dto.PeliculaDto;
import com.sgc.Model.entity.Pelicula;
import com.sgc.Model.service.IPeliculaService;

@Service
public class PeliculaImpl implements IPeliculaService{
    @Autowired //nos deja instanciar la clase PeliculaDao
    private PeliculaDao peliculaDao;


    @Override
    public boolean existsBy(Integer id){
        return peliculaDao.existsById(id);
    }

    @Transactional
    @Override
    public Pelicula save(PeliculaDto peliculaDto) {
        Pelicula pelicula = Pelicula.builder()
            .idPelicula(peliculaDto.getIdPelicula())
            .nombrePelicula(peliculaDto.getNombrePelicula())
            .tituloOriginal(peliculaDto.getTituloOriginal())
            .duracion(peliculaDto.getDuracion())
            .anioEstreno(peliculaDto.getAnioEstreno())
            .descripcion(peliculaDto.getDescripcion())
        .build();

        return peliculaDao.save(pelicula);
    }

    @Transactional(readOnly = true)
    @Override
    public Pelicula findById(Integer id) {
        return peliculaDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
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
