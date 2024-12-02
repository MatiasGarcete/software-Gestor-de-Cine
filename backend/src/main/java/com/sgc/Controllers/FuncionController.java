package com.sgc.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgc.Model.Payload.MensajeResponse;
import com.sgc.Model.dto.FuncionDto;
import com.sgc.Model.entity.Funcion;
import com.sgc.Model.service.IFuncionService;


@RestController
@RequestMapping("api/v1")
public class FuncionController {
    @Autowired
    private IFuncionService funcionService;

    @PostMapping("funcion")
    public ResponseEntity<?> create(@RequestBody FuncionDto funcionDto) {
        Funcion funcionSave = null;
        try {
            funcionSave = funcionService.save(funcionDto);
            return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Guardado Correctamente!")
                        .objeto(
                            Funcion.builder()
                                .idfuncion(funcionSave.getIdfuncion())
                                .capacidad(funcionSave.getCapacidad())
                                .fecha(funcionSave.getFecha())
                                .hora(funcionSave.getHora())
                                .precio(funcionSave.getPrecio())
                            .build()
                        )
                    .build(),
                HttpStatus.CREATED
            );
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje(exDT.getMessage())
                    .objeto(null)
                    .build(),
                HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    
    @PutMapping("funcion/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody FuncionDto funcionDto) {
        Funcion funcionSave = null;
        try {
            if(funcionService.existsBy(id)){
                funcionDto.setIdfuncion(id);
                funcionSave = funcionService.save(funcionDto);
                return new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje("Guardado Correctamente!")
                        .objeto(
                            Funcion.builder()
                                .idfuncion(funcionSave.getIdfuncion())
                                .capacidad(funcionSave.getCapacidad())
                                .fecha(funcionSave.getFecha())
                                .hora(funcionSave.getHora())
                                .precio(funcionSave.getPrecio())
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
                HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @DeleteMapping("funcion/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Funcion funcionDelete = null;
        try {
            funcionDelete = funcionService.findById(id);
            funcionService.delete(funcionDelete);
            return new ResponseEntity<>(funcionDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje(exDT.getMessage())
                    .objeto(null)
                    .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("funcion/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Funcion funcion = funcionService.findById(id);
        if(funcion == null){
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
                    Funcion.builder()
                        .idfuncion(funcion.getIdfuncion())
                        .capacidad(funcion.getCapacidad())
                        .fecha(funcion.getFecha())
                        .hora(funcion.getHora())
                        .precio(funcion.getPrecio())
                    .build()
                )
                .build(),
            HttpStatus.OK
        );
    }

    @GetMapping("funciones/pelicula")
    public List<Integer> obtenerIdsPeliculas() {
        return funcionService.getPeliculasIdsUnicos();
    }
    
    
    @GetMapping("funcion")
    public Iterable<Funcion> showAll() {
        return funcionService.findAll();
    }
}
