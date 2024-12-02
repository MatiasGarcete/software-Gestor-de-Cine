package com.sgc.Model.dto;

import java.io.Serializable;
import java.util.List;

import com.sgc.Model.entity.Funcion;

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
    
    private Integer anioEstreno;
    private Integer duracion;
    private String portada;

    private Integer idGenero;
    private Integer idCalificacion;
    private List<Funcion> funcion;  // Relación con las reservas

}
