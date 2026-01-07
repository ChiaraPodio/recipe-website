/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chiarapodio
 */
@Getter @Setter
@Entity
public class PasoReceta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long codigo_paso;
    private Integer numero_paso;
    private String descripcion;


    public PasoReceta() {
    }

    public PasoReceta(Long codigo_paso, Integer numero_paso, String descripcion) {
        this.codigo_paso = codigo_paso;
        this.numero_paso = numero_paso;
        this.descripcion = descripcion;
    }
    
    
    
}
