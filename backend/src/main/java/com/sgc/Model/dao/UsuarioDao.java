package com.sgc.Model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sgc.Model.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer>{
    // Boolean validacionLogin(String correo, String password);
   @Query(value = "SELECT u FROM Usuario u WHERE u.correo = :correo")
    Usuario buscarCorreo(@Param("correo") String correo);

}
