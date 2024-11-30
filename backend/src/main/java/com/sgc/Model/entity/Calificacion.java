package com.sgc.Model.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "calificar")
public class Calificacion implements Serializable{
    @Id
    @Column(name = "idcalificacion", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcalificacion;

    @Column(name = "calificacion")
    private String calificacion;

    @OneToMany(mappedBy = "calificacion", fetch = FetchType.LAZY)
    @JsonManagedReference("pelicula-calificacion")
    private List<Pelicula> peliculas;
}
