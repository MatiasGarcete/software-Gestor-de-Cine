package com.sgc.Model.dto;

import java.io.Serializable;
import java.util.List;

import com.sgc.Model.entity.Pelicula;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class GeneroDto implements Serializable{
    private Integer idgenero;
    private String genero;

    private List<Pelicula> pelicula;
}
