package com.sgc.Model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol", nullable = false, unique = true)
    private Integer idRol;

    @Column(name = "nombrerol", length = 45, nullable = false)
    private String nombreRol;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY) // OneToMany, con el mapeo hacia la entidad Usuario
    private List<Usuario> usuarios; // Aquí agregamos la lista de usuarios asociados a este rol

}

