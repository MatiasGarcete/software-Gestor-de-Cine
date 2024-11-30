package com.sgc.Model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class FuncionDto implements Serializable {
    private Integer idfuncion;
    private Date fecha;
    private Time hora;
    private Integer capacidad;
    private float precio;
}
