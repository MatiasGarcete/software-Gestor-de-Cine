package com.sgc.Model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.sgc.Model.entity.Funcion;
import com.sgc.Model.entity.Reserva;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PeliculaDto implements Serializable {
    private Integer idPelicula;
    private String nombrePelicula;
    private String tituloOriginal;
    private String descripcion;
    
    private Date anioEstreno;
    private Integer duracion;

    private Integer idGenero;
    private Integer idCalificacion;
    private List<Funcion> funcion;  // Relación con las reservas

}
