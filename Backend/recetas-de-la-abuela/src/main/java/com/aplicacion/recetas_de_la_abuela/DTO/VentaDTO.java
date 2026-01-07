/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.DTO;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chiarapodio
 */
@Getter @Setter
public class VentaDTO {
    
    private Long idSuscriptor;
    private List<DetalleVentaDTO> detalleVenta;

    public VentaDTO() {
    }

    public VentaDTO(Long idSuscriptor, List<DetalleVentaDTO> detalleVenta) {
        this.idSuscriptor = idSuscriptor;
        this.detalleVenta = detalleVenta;
    }
    
}
