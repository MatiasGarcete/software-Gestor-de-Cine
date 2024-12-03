package com.sgc.Model.service;

import com.sgc.Model.dto.UsuarioDto;
import com.sgc.Model.entity.Usuario;

public interface IUsuarioService {
    /*****Guarda un usuario nuevo*****
     * No solo nos creara un nuevo usario, sino que tambien 
     * verifica que el regristro no exista, en el caso de exita el 
     * registro se actualizara.
     ******/
    Usuario save(UsuarioDto usuario);

    /*****Muestra un usuario segun la id*****
     * Busca a un usuario por medio la ID
     */
    Usuario findById(Integer id);
    Iterable<Usuario> findAll();
    /*****Elimina el usuario*****
     * Se podria hacer por medio de ID, pero este metodo toma
     * todo los datos y lo elimina
     *****/
    void delete(Usuario usuario);
    boolean existsBy(Integer id);
    // Usuario validarLogin(String correo, String password);
    Integer buscarCorreo(String correo);

}