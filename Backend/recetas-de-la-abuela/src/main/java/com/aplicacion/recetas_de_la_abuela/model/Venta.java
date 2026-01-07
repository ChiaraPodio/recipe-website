/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chiarapodio
 */
@Getter @Setter
@Entity
public class Venta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
        
    @ManyToOne
    @JoinColumn(name = "idSuscriptor")
    private Suscriptor unSuscriptor; 
    
    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> listaDetalleVenta;

    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, Suscriptor unSuscriptor, List<DetalleVenta> listaDetalleVenta) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.unSuscriptor = unSuscriptor;
        this.listaDetalleVenta = listaDetalleVenta;
    }
     
}
