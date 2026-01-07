/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.DTO;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chiarapodio
 */
@Getter @Setter
public class SuscriptorDTO {
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private String contrasena;
    private Long celular;
    private String correo_electronico;
    private String provincia;
    private String ciudad;
    private String direccion;
    private String preferenciaDeReceta;
    private Boolean recibir_ofertas;

    public SuscriptorDTO() {
    }

    public SuscriptorDTO(String nombre, String apellido, LocalDate fecha_nacimiento, String contrasena, Long celular, String correo_electronico, String provincia, String ciudad, String direccion, String preferenciaDeReceta, Boolean recibir_ofertas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.contrasena = contrasena;
        this.celular = celular;
        this.correo_electronico = correo_electronico;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.preferenciaDeReceta = preferenciaDeReceta;
        this.recibir_ofertas = recibir_ofertas;
    }

   

    

    
    
    
    
}
