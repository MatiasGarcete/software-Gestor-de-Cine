package com.sgc.Model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.UsuarioDao;
import com.sgc.Model.dto.UsuarioDto;
import com.sgc.Model.entity.Rol;
import com.sgc.Model.entity.Usuario;
import com.sgc.Model.service.IRolService;
import com.sgc.Model.service.IUsuarioService;

@Service
public class UsuarioImpl implements IUsuarioService{

    //nos brinda control a la hora de inyectar dependecias o instancias que se almacena en Spring
    @Autowired 
    private UsuarioDao usuarioDao;
    @Autowired
    private IRolService rolService;

    @Override
    public boolean existsBy(Integer id){
        return usuarioDao.existsById(id);
    }

    @Transactional
    @Override
    public Usuario save(UsuarioDto usuarioDto) {
        // Buscar el rol desde la base de datos usando el idRol
        Rol rol = rolService.findById(usuarioDto.getIdRol()); 
        //Setteamos todos los valores que vienen del UsuarioDto para generar un Objeto Usuario Entity
        Usuario usuario = Usuario.builder()
            .idUsuario(usuarioDto.getIdUsuario())
            .nombre(usuarioDto.getNombre())
            .apellido(usuarioDto.getApellido())
            .password(usuarioDto.getPassword())
            .correo(usuarioDto.getCorreo())
            .rol(rol)
            .reservas(usuarioDto.getReservas())
            .build();
        return usuarioDao.save(usuario);
    }
    @Transactional(readOnly = true) //Porque es una consulta de lectura
    @Override
    public Usuario findById(Integer id) {
        //Aca vamos a buscar al usuario segun la id pero se no se encuentra devolvemos null
        return usuarioDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true) //Porque es una consulta de lectura
    @Override
    public Iterable<Usuario> findAll() {
        //Aca vamos a buscar al usuario segun la id pero se no se encuentra devolvemos null
        return usuarioDao.findAll();
    }

    @Transactional
    @Override
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Transactional
    @Override
    public Integer buscarCorreo(String correo){
        return usuarioDao.buscarCorreo(correo);
    }
    
}
