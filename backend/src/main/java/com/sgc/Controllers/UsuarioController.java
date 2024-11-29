package com.sgc.Controllers;
import org.springframework.web.bind.annotation.RestController;

import com.sgc.Model.Payload.MensajeResponse;
import com.sgc.Model.dto.UsuarioDto;
import com.sgc.Model.entity.Usuario;
import com.sgc.Model.service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    private IUsuarioService usuarioService;

    /**METODOS DEL IUsuario **/
    @PostMapping("usuario")
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioSave = null;
        try {
            usuarioSave = usuarioService.save(usuarioDto);

            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("Guadado Correctamente")
                    .objeto(
                        UsuarioDto.builder()
                            .idUsuario(usuarioSave.getIdUsuario())
                            .nombre(usuarioSave.getNombre())
                            .apellido(usuarioSave.getApellido())
                            .password(usuarioSave.getPassword())
                            .correo(usuarioSave.getCorreo())
                        .build()
                    )
                    .build(),
                HttpStatus.CREATED);
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje(exDT.getMessage())
                    .objeto(null)
                    .build(),
                HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


    @PutMapping("usuario/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer id){
        Usuario usuarioUpdate = null;
        try {
            if(usuarioService.existsBy(id)){
                usuarioDto.setIdUsuario(id); //Por precausion setteamos el id que viene en el parametro 
                usuarioUpdate = usuarioService.save(usuarioDto);
                return new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje("Guadado Correctamente")
                        .objeto(
                            UsuarioDto.builder()
                                .idUsuario(usuarioUpdate.getIdUsuario())
                                .nombre(usuarioUpdate.getNombre())
                                .apellido(usuarioUpdate.getApellido())
                                .password(usuarioUpdate.getPassword())
                                .correo(usuarioUpdate.getCorreo())
                            .build()
                        )
                        .build(),
                    HttpStatus.CREATED
                );
            }
            else{
                return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("El registro que intenta actualizar no existe en la base de datos")
                    .objeto(null)
                    .build(),
                HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje(exDT.getMessage())
                    .objeto(null)
                    .build(),
                HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("usuario/{id}")
    //Objeto que no es reconocido
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Usuario usuarioDelete = null;
        try {
            //Buscamos el usuario para obtener toda la info y lo guardamos en una variable
            usuarioDelete = usuarioService.findById(id);
            //Teniendo toda la informacion del usuario eliminamos
            usuarioService.delete(usuarioDelete);
            return new ResponseEntity<>(usuarioDelete, HttpStatus.NO_CONTENT);
        
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje(exDT.getMessage())
                    .objeto(null)
                    .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("usuario/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);
        if(usuario == null){
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar, no existe!!")
                    .objeto(null)
                    .build(),
                HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
            MensajeResponse.builder()
                .mensaje("")
                .objeto(
                    UsuarioDto.builder()
                        .idUsuario(usuario.getIdUsuario())
                        .nombre(usuario.getNombre())
                        .apellido(usuario.getApellido())
                        .password(usuario.getPassword())
                        .correo(usuario.getCorreo())
                    .build()
                )
                .build(),
            HttpStatus.OK
        );

    }

    @GetMapping("usuario")
    @ResponseStatus(HttpStatus.OK) //Estado de la solicitud 
    public Iterable<Usuario> showAll() {
        return usuarioService.findAll();
    }
    

}
