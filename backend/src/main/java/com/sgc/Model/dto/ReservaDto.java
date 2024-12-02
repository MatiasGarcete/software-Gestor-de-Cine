package com.sgc.Model.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ReservaDto implements Serializable{
    private Integer idreserva;
    private Date fechaReserva;
    private Integer cantidadEntradas;
    private float total;

    private Integer idUsuario;
    private Integer idFuncion;
}
