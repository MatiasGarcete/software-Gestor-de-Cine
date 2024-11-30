package com.sgc.Model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

import com.sgc.Model.entity.Reserva;


@Data
@ToString
@Builder
public class UsuarioDto implements Serializable {

    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String password;
    private String correo;

    private Integer idRol;  // Relacion con la Tabla Rol
    private List<Reserva> reservas;  // Relación con las reservas
}

