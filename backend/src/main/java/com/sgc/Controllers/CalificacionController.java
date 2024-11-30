package com.sgc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgc.Model.Payload.MensajeResponse;
import com.sgc.Model.dto.CalificacionDto;
import com.sgc.Model.entity.Calificacion;
import com.sgc.Model.service.ICalificacionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1")
public class CalificacionController {
    @Autowired
    private ICalificacionService calificacionService;

    @PostMapping("calificacion")
    public ResponseEntity<?> create(@RequestBody CalificacionDto calificacionDto) {
        Calificacion calificacionSave = null;
        try {
            if(!calificacionService.existsByCalificacion(calificacionDto.getCalificacion())){
                calificacionSave = calificacionService.save(calificacionDto);
                return new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje("Guardado Correctamente!")
                        .objeto(
                            CalificacionDto.builder()
                                .idcalificacion(calificacionSave.getIdcalificacion())
                                .calificacion(calificacionSave.getCalificacion())
                            .build()
                        )
                        .build(),
                        HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("La calificacion que quiere crear ya existe")
                    .objeto(null)
                    .build(),
                HttpStatus.METHOD_NOT_ALLOWED);
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
    
    @PutMapping("calificacion/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CalificacionDto calificacionDto) {
        Calificacion calificacionSave = null;
        try {
            if(calificacionService.existsBy(id)){
                calificacionDto.setIdcalificacion(id);
                calificacionSave = calificacionService.save(calificacionDto);

                return new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje("Guardado Correctamente!")
                        .objeto(
                            CalificacionDto.builder()
                                .idcalificacion(calificacionSave.getIdcalificacion())
                                .calificacion(calificacionSave.getCalificacion())
                            .build())
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

    @DeleteMapping("calificacion/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Calificacion calificacionDelete = null;
        try {
            calificacionDelete = calificacionService.findById(id);
            calificacionService.delete(calificacionDelete);
            return new ResponseEntity<>(calificacionDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje(exDT.getMessage())
                    .objeto(null)
                    .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("calificacion/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Calificacion calificacion = calificacionService.findById(id);
        if (calificacion == null) {
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
                    CalificacionDto.builder()
                        .idcalificacion(calificacion.getIdcalificacion())
                        .calificacion(calificacion.getCalificacion())
                    .build())
            .build(),
            HttpStatus.OK
        );
    }

    @GetMapping("calificacion")
    public Iterable<Calificacion> findAll() {
        return calificacionService.findAll();
    }
    
}
