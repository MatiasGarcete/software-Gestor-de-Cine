package com.sgc.Model.service;

import com.sgc.Model.dto.ReservaDto;
import com.sgc.Model.entity.Reserva;

public interface IReservaService {
    Reserva save(ReservaDto reservaDto);
    Reserva findById(Integer id);
    Iterable<Reserva> findAll();
    void delete(Reserva reserva);
    boolean existsBy(Integer id);
}
