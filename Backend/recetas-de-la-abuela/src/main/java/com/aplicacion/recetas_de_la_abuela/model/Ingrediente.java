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
public class Ingrediente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long codigo_ingrediente;
    private String nombre;
    private Double cantidad;
    private String unidad_medida;


    public Ingrediente() {
    }

    public Ingrediente(Long codigo_ingrediente, String nombre, Double cantidad, String unidad_medida) {
        this.codigo_ingrediente = codigo_ingrediente;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad_medida = unidad_medida;
    }
    
    
    
}
