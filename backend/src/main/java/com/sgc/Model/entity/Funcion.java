package com.sgc.Model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

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
@Table(name = "funcion")
public class Funcion implements Serializable{
    @Id
    @Column(name = "idfuncion", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idfuncion;
    
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "hora")
    private Time hora;

    @Column(name = "entrada")
    private Integer capacidad;

    @Column(name = "precio")
    private float precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpelicula", nullable = false) // Relación hacia Calificacion
    @JsonBackReference("pelicula-funcion")
    private Pelicula pelicula;
}
