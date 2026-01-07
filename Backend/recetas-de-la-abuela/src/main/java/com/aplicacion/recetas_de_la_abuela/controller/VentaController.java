/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.controller;

import com.aplicacion.recetas_de_la_abuela.DTO.DetalleVentaDTO;
import com.aplicacion.recetas_de_la_abuela.DTO.VentaDTO;
import com.aplicacion.recetas_de_la_abuela.model.Producto;
import com.aplicacion.recetas_de_la_abuela.model.Venta;
import com.aplicacion.recetas_de_la_abuela.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chiarapodio
 */
@RestController
public class VentaController {
    
     @Autowired
    private IVentaService ventaServ;
    
    @PostMapping("/ventas/crear")
    public String saveVenta (@RequestBody VentaDTO ventaDto) {
        
        
        ventaServ.crearVenta(ventaDto);
        
        return "Venta creada correctamente";
    }
    
    @GetMapping("/venta/{codigo_venta}")
    public Venta findVenta (@PathVariable Long codigo_venta) {
        return ventaServ.findVenta(codigo_venta);
    }
    
    @GetMapping("/ventas")
    public List<Venta> getVentas() {
        return ventaServ.getVentas();
    }
    
    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<Producto> getProductosDeVenta(@PathVariable Long codigo_venta) {
        return ventaServ.getProductosDeVenta(codigo_venta);
    }
    
    @GetMapping("/ventas/dia/{fecha_venta}")
    public String getTotalMontoYVentas(@PathVariable LocalDate fecha_venta) {
        return ventaServ.getTotalMontoYVentas(fecha_venta);
    }
    
    @PutMapping("/ventas/editar/{codigo_venta}")
    public Venta editVenta(@PathVariable Long codigo_venta,
                                    @RequestParam (required = false) LocalDate nuevaFecha_venta,
                                    @RequestParam (required = false) Long idClienteNuevo,
                                    @RequestParam (required = false) List<DetalleVentaDTO> listaDetalleNueva) {
        
        return ventaServ.editVenta(codigo_venta, nuevaFecha_venta, idClienteNuevo, listaDetalleNueva);
    }
    
     @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public String deleteVenta(@PathVariable Long codigo_venta) {
        ventaServ.deleteVenta(codigo_venta);
        return "Venta eliminada exitosamente";
    }
    
}
