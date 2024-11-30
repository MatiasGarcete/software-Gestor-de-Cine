package com.sgc.Model.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "pelicula")
public class Pelicula implements Serializable{

    @Id
    @Column(name = "idPelicula", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPelicula;
    @Column(name = "nombrePelicula", nullable = false)
    private String nombrePelicula;
    @Column(name = "tituloOriginal", nullable = false)
    private String tituloOriginal;
    @Column(name = "duracion", nullable = false)
    private Integer duracion;
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "anioEstreno", nullable = false)
    private Date anioEstreno;
    
}

