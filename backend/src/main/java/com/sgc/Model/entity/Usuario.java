package com.sgc.Model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "idusuario") //Nombre que reprensetan en la Tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrementable
    private Integer idusuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rol")
    private String rol;
    
    @Column(name = "correo")
    private String correo;

}
