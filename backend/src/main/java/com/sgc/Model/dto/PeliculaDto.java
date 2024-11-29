package com.sgc.Model.dto;

import java.io.Serializable;

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
    private Integer duracion;
}
