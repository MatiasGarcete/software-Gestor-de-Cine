package com.sgc.Model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class GeneroDto implements Serializable{
    private Integer idgenero;
    private String genero;
}
