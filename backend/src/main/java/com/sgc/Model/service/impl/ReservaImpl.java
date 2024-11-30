package com.sgc.Model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.ReservaDao;
import com.sgc.Model.dto.ReservaDto;
import com.sgc.Model.entity.Reserva;
import com.sgc.Model.entity.Usuario;
import com.sgc.Model.service.IReservaService;
import com.sgc.Model.service.IUsuarioService;

@Service
public class ReservaImpl implements IReservaService{

    @Autowired
    private ReservaDao reservaDao;
    @Autowired
    private IUsuarioService usuarioService;

    @Transactional
    @Override
    public Reserva save(ReservaDto reservaDto) {
        Usuario usuario = usuarioService.findById(reservaDto.getIdUsuario());

        Reserva reserva = Reserva.builder()
            .idreserva(reservaDto.getIdreserva())
            .fechaReserva(reservaDto.getFechaReserva())
            .cantidadEntradas(reservaDto.getCantidadEntradas())
            .usuario(usuario)
        .build();

        return reservaDao.save(reserva);
    }

    @Transactional(readOnly = true)
    @Override
    public Reserva findById(Integer id) {
        return reservaDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Reserva> findAll() {
        return reservaDao.findAll();
    }

    @Transactional
    @Override
    public void delete(Reserva reserva) {
        reservaDao.delete(reserva);
    }

    @Transactional
    @Override
    public boolean existsBy(Integer id) {
        return reservaDao.existsById(id);
    }
    
}
