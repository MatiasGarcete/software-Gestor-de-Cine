package com.sgc.Model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.CalificacionDao;
import com.sgc.Model.dto.CalificacionDto;
import com.sgc.Model.entity.Calificacion;
import com.sgc.Model.service.ICalificacionService;

@Service
public class CalificacionImpl implements ICalificacionService{
    @Autowired
    private CalificacionDao calificacionDao;

    @Transactional
    @Override
    public Calificacion save(CalificacionDto calificacionDto) {
        Calificacion calificacion = Calificacion
            .builder()
                .idcalificacion(calificacionDto.getIdcalificacion())
                .calificacion(calificacionDto.getCalificacion())
            .build();
        return calificacionDao.save(calificacion);
    }

    @Transactional(readOnly = true)
    @Override
    public Calificacion findById(Integer id) {
        return calificacionDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Calificacion> findAll() {
        return calificacionDao.findAll();
    }

    @Transactional
    @Override
    public void delete(Calificacion calificacion) {
        calificacionDao.delete(calificacion);
    }

    @Transactional
    @Override
    public boolean existsBy(Integer id) {
        return calificacionDao.existsById(id);
    }

    @Override
    public boolean existsByCalificacion(String nombre) {
        return calificacionDao.existsByCalificacion(nombre);
    }


}
