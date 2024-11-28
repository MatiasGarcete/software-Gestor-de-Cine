package com.sgc.Model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.UsuarioDao;
import com.sgc.Model.entity.Usuario;
import com.sgc.Model.service.IUsuario;

@Service
public class UsuarioImpl implements IUsuario{

    //nos brinda control a la hora de inyectar dependecias o instancias que se almacena en Spring
    @Autowired 
    private UsuarioDao usuarioDao;

    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }
    @Transactional(readOnly = true) //Porque es una consulta de lectura
    @Override
    public Usuario findById(Integer id) {
        //Aca vamos a buscar al usuario segun la id pero se no se encuentra devolvemos null
        return usuarioDao.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
    
}
