package com.sgc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgc.Model.Payload.MensajeResponse;
import com.sgc.Model.dto.GeneroDto;
import com.sgc.Model.entity.Genero;
import com.sgc.Model.service.IGeneroService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1")
public class GeneroController {
    @Autowired
    private IGeneroService generoService;

    @PostMapping("genero")
    public ResponseEntity<?> create(@RequestBody GeneroDto generoDto){
        Genero generoSave = null;
        try {
            if(!generoService.existsByGenero(generoDto.getGenero())){
            generoSave = generoService.save(generoDto);
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("Guadado Correctamente")
                    .objeto(
                        GeneroDto.builder()
                            .idgenero(generoSave.getIdgenero())
                            .genero(generoSave.getGenero())
                        .build()
                    )
                    .build(),
                HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("Error. Este Genero ya existe")
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

    @PutMapping("genero/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody GeneroDto generoDto) {
        Genero generoSave = null;
        try {
            if (generoService.existsBy(id)) {
                generoDto.setIdgenero(id);
                generoSave = generoService.save(generoDto);

                return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("Guadado Correctamente")
                    .objeto(
                        GeneroDto.builder()
                            .idgenero(generoSave.getIdgenero())
                            .genero(generoDto.getGenero())
                        .build()
                    )
                    .build(),
                HttpStatus.CREATED);
            }else{
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

    @DeleteMapping("genero/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Genero generoDelete = null;
        try {
            generoDelete = generoService.findById(id);
            generoService.delete(generoDelete);
            return new ResponseEntity<>(generoDelete, HttpStatus.NO_CONTENT);
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

    @GetMapping("genero/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Genero genero = generoService.findById(id);
        if(genero == null){
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
                    GeneroDto.builder()
                        .idgenero(genero.getIdgenero())
                        .genero(genero.getGenero())
                    .build()
                )
            .build(),
            HttpStatus.OK
        );
    }
    
    @GetMapping("genero")
    public Iterable<Genero> findAll() {
        return generoService.findAll();
    }
    
}
