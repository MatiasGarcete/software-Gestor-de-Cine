package com.sgc.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgc.Model.Payload.MensajeResponse;
import com.sgc.Model.dto.PeliculaDto;
import com.sgc.Model.entity.Pelicula;
import com.sgc.Model.service.IPeliculaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1")
public class PeliculaController {
    @Autowired
    private IPeliculaService peliculaService;

    @PostMapping("pelicula")
    public ResponseEntity<?> create(@RequestBody PeliculaDto peliculaDto) { 
        Pelicula peliculaSave = null;
        try {
            peliculaSave = peliculaService.save(peliculaDto);
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("Guadado Correctamente")
                    .objeto(
                        PeliculaDto.builder()
                            .idPelicula(peliculaSave.getIdPelicula())
                            .nombrePelicula(peliculaSave.getNombrePelicula())
                            .tituloOriginal(peliculaSave.getTituloOriginal())
                            .duracion(peliculaSave.getDuracion())
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
    @PutMapping("pelicula/{id}")
    public ResponseEntity<?> update(@RequestBody PeliculaDto peliculaDto, @PathVariable Integer id) {
        Pelicula peliculaSave = null;
        try {
            if(peliculaService.existsBy(id)){
                peliculaDto.setIdPelicula(id);
                peliculaSave = peliculaService.save(peliculaDto);

                return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("Guadado Correctamente")
                    .objeto(
                        PeliculaDto.builder()
                            .idPelicula(peliculaSave.getIdPelicula())
                            .nombrePelicula(peliculaSave.getNombrePelicula())
                            .tituloOriginal(peliculaSave.getTituloOriginal())
                            .duracion(peliculaSave.getDuracion())
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

    @DeleteMapping("pelicula/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Pelicula peliculaDelete = null;
        try {
            peliculaDelete = peliculaService.findById(id);
            peliculaService.delete(peliculaDelete);
            return new ResponseEntity<>(peliculaDelete, HttpStatus.NO_CONTENT);
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

    @GetMapping("pelicula/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Pelicula pelicula = peliculaService.findById(id);
        if(pelicula == null){
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
                    PeliculaDto.builder()
                            .idPelicula(pelicula.getIdPelicula())
                            .nombrePelicula(pelicula.getNombrePelicula())
                            .tituloOriginal(pelicula.getTituloOriginal())
                            .duracion(pelicula.getDuracion())
                        .build()
                ),
            HttpStatus.OK
        );
    }
    
    @GetMapping("peliculas")
    public Iterable<Pelicula> findAll() {
        return peliculaService.findAll();
    }
}
