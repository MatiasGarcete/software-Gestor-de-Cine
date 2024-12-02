package com.sgc.Model.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private Integer anioEstreno;

    @Column(name = "portada")
    private String portada;

    // //Relaciones
    // En Pelicula.java
    @OneToMany(mappedBy = "pelicula", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
    @JsonManagedReference("pelicula-funcion") // Maneja la serialización JSON
    private List<Funcion> funciones; // Lista de funciones asociadas a esta película


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idgenero", nullable = false) // Relación hacia Genero
    @JsonBackReference("pelicula-genero")
    private Genero genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcalificacion", nullable = false) // Relación hacia Calificacion
    @JsonBackReference("pelicula-calificacion")
    private Calificacion calificacion;

}

