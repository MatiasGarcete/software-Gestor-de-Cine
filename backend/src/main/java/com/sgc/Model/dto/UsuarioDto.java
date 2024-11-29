package com.sgc.Model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
@Builder
public class UsuarioDto implements Serializable {

    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String password;
    private String correo;


}

