package com.sgc.Model.dao;

import org.springframework.data.repository.CrudRepository;

import com.sgc.Model.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer>{

    
}
