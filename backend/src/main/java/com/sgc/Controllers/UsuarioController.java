package com.sgc.Controllers;
import org.springframework.web.bind.annotation.RestController;

import com.sgc.Model.entity.Usuario;
import com.sgc.Model.service.IUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController //esta clase puede manejar peticiones GET - POST - PUT - DELETE
@RequestMapping("/api/v1")
public class UsuarioController {
    //llamamos a nuestro servicio
    @Autowired
    private IUsuario usuarioService;

    /**METODOS DEL IUsuario **/
    @PostMapping("usuario")
    public Usuario create(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PutMapping("usuario")
    public Usuario update(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @DeleteMapping("usuario/{id}")
    public void delete(@PathVariable Integer id){
        //Buscamos el usuario para obtener toda la info y lo guardamos en una variable
        Usuario usuarioDelete = usuarioService.findById(id);
        //Teniendo toda la informacion del usuario eliminamos
        usuarioService.delete(usuarioDelete);
    }

    @GetMapping("usuario/{id}")
    public Usuario showById(@PathVariable Integer id){
        return usuarioService.findById(id);
    }
}
