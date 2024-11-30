package com.sgc.Model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RolDto implements Serializable {
    private Integer idRol;
    private String nombreRol;
    private List<UsuarioDto> usuario;  // Relación con los usuario
}
