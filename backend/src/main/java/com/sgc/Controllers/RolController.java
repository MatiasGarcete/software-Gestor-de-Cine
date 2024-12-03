package com.sgc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgc.Model.Payload.MensajeResponse;
import com.sgc.Model.dto.RolDto;
import com.sgc.Model.entity.Rol;
import com.sgc.Model.service.IRolService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1")
public class RolController {
    @Autowired
    private IRolService rolService;

    @PostMapping("rol")
    public ResponseEntity<?> create(@RequestBody RolDto rolDto){
        Rol rolSave = null;
        try {
            //Validamos si ya existe
            if(!rolService.existsByNombre(rolDto.getNombreRol())){
                rolSave = rolService.save(rolDto); //Guardamos el rol
                //Devolvemos un objeto
                return new ResponseEntity<>( 
                    MensajeResponse.builder()
                        .mensaje("Guadado Correctamente")
                        .objeto(
                            RolDto.builder() //creamos un objeto RolDto y asignamos los datos
                                .idRol(rolSave.getIdRol())
                                .nombreRol(rolSave.getNombreRol())
                            .build()
                        )
                    .build(),
                HttpStatus.CREATED); //Esto de la solicitud
            }
            else{
                return new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje("Error. Este Rol ya existe")
                        .objeto(null)
                        .build(),
                    HttpStatus.CONFLICT);
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

    @GetMapping("rolAll")
    public Iterable<Rol> findAll() {
        return rolService.findAll();
    }
    
    @GetMapping("rol/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Rol rol = null;
        try {
            rol = rolService.findById(id);
            if(rol != null) return new ResponseEntity<>(rol, HttpStatus.OK);
            else return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("El rol no existe")
                    .objeto(null)
                    .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje(exDT.getMessage())
                    .objeto(null)
                    .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("rol/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Rol rolDelete = null;
        try {
            rolDelete = rolService.findById(id);
            if(rolDelete.getUsuarios().isEmpty()){
                rolService.delete(rolDelete);
                return new ResponseEntity<>(rolDelete, HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("No se puede Eliminar este rol, tiene usuario vinculados!")
                    .objeto(null)
                    .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje(exDT.getMessage())
                    .objeto(null)
                    .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
