package com.sgc.Model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sgc.Model.entity.Usuario;
import java.util.List;


public interface UsuarioDao extends CrudRepository<Usuario, Integer>{
    // Boolean validacionLogin(String correo, String password);
    //@Query(value = "SELECT * FROM usuario u WHERE u.correo = :correo", nativeQuery = true)
    // @Query(value = "SELECT u.* FROM usuario u JOIN rol r ON u.idrol = r.idrol WHERE u.correo = :correo", nativeQuery = true)
    // Usuario buscarCorreo(@Param("correo") String correo);
    // Usuario findByCorreo(String correo);
    @Query(value = "SELECT u.idusuario FROM usuario u WHERE u.correo = :correo", nativeQuery = true)
    Integer buscarCorreo(@Param("correo") String correo);
}
