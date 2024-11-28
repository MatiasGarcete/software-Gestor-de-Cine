package com.sgc.Model.entity;

import java.io.Serializable;

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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
    //Atributos de la Tabla
    @Id //Indicamos que es una ID
    @Column(name = "idusuario", nullable = false) //Nombre que reprensetan en la Tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrementable
    private Integer idusuario;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "correo", nullable = false)
    private String correo;

    // Relación Many-to-One con la entidad Rol
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrol", referencedColumnName = "idrol", nullable = false)
    private Rol rol;

    //------Obtener los atributos-----------
    // Getters y Setters
    public Integer getRol() {
        //rol es un objeto que nos trae tanto la id como el nombre
        //rol.getIdrol para que nos de el valor interger
        return rol.getIdrol();
    }
}
