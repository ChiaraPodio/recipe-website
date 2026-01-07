/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chiarapodio
 */
@Getter @Setter
@Entity
public class Receta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long codigo_receta;
    private String nombre;
    private String descripcion;
    private String tiempo_preparacion;
    private String categoria;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "codigo_receta")
    private List<Ingrediente> listaIngredientes;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "codigo_receta")
    private List<PasoReceta> listaPasosReceta;

    public Receta() {
    }

    public Receta(Long codigo_receta, String nombre, String descripcion, String tiempo_preparacion, String categoria, List<Ingrediente> listaIngredientes, List<PasoReceta> listaPasosReceta) {
        this.codigo_receta = codigo_receta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempo_preparacion = tiempo_preparacion;
        this.categoria = categoria;
        this.listaIngredientes = listaIngredientes;
        this.listaPasosReceta = listaPasosReceta;
    }

    
    
    
}
