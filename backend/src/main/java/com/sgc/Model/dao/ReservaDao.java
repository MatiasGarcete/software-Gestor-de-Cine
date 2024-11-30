package com.sgc.Model.dao;

import org.springframework.data.repository.CrudRepository;
import com.sgc.Model.entity.Reserva;

public interface ReservaDao extends CrudRepository<Reserva, Integer>{
    
}
