 package com.sgc.Model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario", nullable = false, unique = true)
    private Integer idUsuario;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;

    @Column(name = "password", length = 45, nullable = false)
    private String password;

    @Column(name = "correo", length = 45, nullable = false)
    private String correo;

    @ManyToOne(fetch = FetchType.LAZY) // Relación Muchos a Uno
    @JoinColumn(name = "idrol", nullable = false) // Clave foránea
    @JsonBackReference // Evita el ciclo de referencia al serializar
    private Rol rol;

    // Relación OneToMany con Reserva
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference("reserva-usuario") // Evita el ciclo de referencia al serializar
    private List<Reserva> reservas;
}

