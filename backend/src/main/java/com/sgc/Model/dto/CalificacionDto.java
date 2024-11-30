package com.sgc.Model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class CalificacionDto implements Serializable{
    private Integer idcalificacion;
    private String calificacion;
}
