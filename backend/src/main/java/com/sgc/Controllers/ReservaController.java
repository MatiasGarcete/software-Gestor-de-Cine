package com.sgc.Controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgc.Model.Payload.MensajeResponse;
import com.sgc.Model.dto.FuncionDto;
import com.sgc.Model.dto.ReservaDto;
import com.sgc.Model.entity.Funcion;
import com.sgc.Model.entity.Reserva;
import com.sgc.Model.service.IFuncionService;
import com.sgc.Model.service.IReservaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1")
public class ReservaController {
    @Autowired
    private IReservaService reservaService;
    @Autowired
    private IFuncionService funcionService;

    @PostMapping("reserva")
    public ResponseEntity<?> create(@RequestBody ReservaDto reservaDto) {
        Reserva reservaSave = null;
        try {
            if(funcionService.existsBy(reservaDto.getIdFuncion())){
                //Buscamos la funcion por id
                Funcion funcion = funcionService.findById(reservaDto.getIdFuncion());
                //Settamos el precio total de la reserva
                reservaDto.setTotal(reservaDto.getCantidadEntradas() * funcion.getPrecio());
                //Setteamos la cantidad que queda en la funcion
                funcion.setCapacidad(funcion.getCapacidad()-reservaDto.getCantidadEntradas());
                //Actualizamos la funcion
                funcionService.save(
                    FuncionDto.builder()
                        .idfuncion(funcion.getIdfuncion())
                        .capacidad(funcion.getCapacidad())
                        .fecha(funcion.getFecha())
                        .hora(funcion.getHora())
                        .precio(funcion.getPrecio())
                        .idPelicula(funcion.getPelicula().getIdPelicula())
                    .build()
                );
                //Guardamos la reserva
                reservaSave = reservaService.save(reservaDto);
                //Respuesta del postman
                return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Guardado Correctamente!")
                            .objeto(
                                ReservaDto.builder()
                                    .idreserva(reservaSave.getIdreserva())
                                    .fechaReserva(reservaSave.getFechaReserva())
                                    .cantidadEntradas(reservaSave.getCantidadEntradas())
                                    .total(reservaDto.getTotal())
                                    .idUsuario(reservaDto.getIdUsuario())
                                    .idFuncion(reservaDto.getIdFuncion())
                                .build()
                            )
                        .build(),
                    HttpStatus.CREATED
                );
            } else{
                return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("La id de la funcion que se quiere reserva no existe.")
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
    
    @PutMapping("reserva/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ReservaDto reservaDto) {
        Reserva reservaSave = null;
        try {
            if(reservaService.existsBy(id)){
                reservaDto.setIdreserva(id);
                reservaSave = reservaService.save(reservaDto);
                return new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje("Guardado Correctamente!")
                        .objeto(
                            ReservaDto.builder()
                                .idreserva(reservaSave.getIdreserva())
                                .fechaReserva(reservaSave.getFechaReserva())
                                .cantidadEntradas(reservaSave.getCantidadEntradas())
                                .total(reservaSave.getTotal())
                                .idUsuario(reservaSave.getUsuario().getIdUsuario())
                                .idFuncion(reservaSave.getFuncion().getIdfuncion())
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

    @DeleteMapping("reserva/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Reserva reservaDelete = null;
        try {
            reservaDelete = reservaService.findById(id);
            reservaService.delete(reservaDelete);
            return new ResponseEntity<>(reservaDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje(exDT.getMessage())
                    .objeto(null)
                    .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("reserva/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Reserva reserva = reservaService.findById(id);
        if(reserva == null){
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
                    ReservaDto.builder()
                        .idreserva(reserva.getIdreserva())
                        .fechaReserva(reserva.getFechaReserva())
                        .cantidadEntradas(reserva.getCantidadEntradas())
                        .total(reserva  .getTotal())
                        .idUsuario(reserva.getUsuario().getIdUsuario())
                        .idFuncion(reserva.getFuncion().getIdfuncion())
                    .build()
                )
                .build(),
            HttpStatus.OK
        );
    }
    
    @GetMapping("reserva")
    public ResponseEntity<?> showAll() {
        List<Reserva> reservas = StreamSupport.stream(
            reservaService.findAll().spliterator(),
            false).collect(Collectors.toList());
        
        if (reservas.isEmpty()) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                    .mensaje("No se encontraron registros!")
                    .objeto(null)
                    .build(),
                HttpStatus.NOT_FOUND
            );
        }
    
        // Convertir cada Reserva a ReservaDto
        List<ReservaDto> reservaDtos = reservas.stream()
            .map(reserva -> ReservaDto.builder()
                .idreserva(reserva.getIdreserva())
                .fechaReserva(reserva.getFechaReserva())
                .cantidadEntradas(reserva.getCantidadEntradas())
                .total(reserva.getTotal())
                .idUsuario(reserva.getUsuario().getIdUsuario())
                .idFuncion(reserva.getFuncion().getIdfuncion())
                .build()
            ).toList();
    
        return new ResponseEntity<>(
            MensajeResponse.builder()
                .mensaje("")
                .objeto(reservaDtos)
                .build(),
            HttpStatus.OK
        );
    }
    
    
}
