package com.sgc.Model.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rol")
public class Rol implements Serializable{
    @Id
    @Column(name = "idrol", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrol;
    
    @Column(name = "nombreRol", nullable = false, unique = true)
    private String nombreRol;

    // Relación One-to-Many con la entidad Usuario - NO AFECTA A LA BDD
    @OneToMany(mappedBy = "rol")  // La propiedad 'rol' en la clase Usuario es la que mantiene la relación
    private List<Usuario> usuarios;

    
}
