package com.sgc.Model.entity;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Reserva")
public class Reserva implements Serializable{
    @Id
    @Column(name = "idreserva", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idreserva;
    
    @Column(name = "fechaReserva")
    private Date fechaReserva;

    @Column(name = "cantidadEntradas")
    private Integer cantidadEntradas;

    @Column(name = "total")
    private float total;

    // Relación ManyToOne con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = false)
    @JsonBackReference("reserva-usuario") // Evita el ciclo de referencia al serializar
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idfuncion", nullable = false) // Relación hacia Calificacion
    @JsonBackReference("reserva-funcion")
    private Funcion funcion;
}
