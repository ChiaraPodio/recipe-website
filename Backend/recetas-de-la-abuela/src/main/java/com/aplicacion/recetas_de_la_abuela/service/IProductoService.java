/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.service;

import com.aplicacion.recetas_de_la_abuela.DTO.DetalleVentaDTO;
import com.aplicacion.recetas_de_la_abuela.DTO.ProductoDTO;
import com.aplicacion.recetas_de_la_abuela.model.Producto;
import com.aplicacion.recetas_de_la_abuela.model.Venta;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author chiarapodio
 */
@Service
public interface IProductoService {
    
    public void saveProducto (Producto producto);
    
     public Producto findProducto (Long codigo_producto);
     
     public List<Producto> getProductos();
     
     public Producto editProducto (Long id_producto, String nuevoNombre, String nuevaDescripcion, String nuevaMarca, Double nuevoPrecioUnitario, Double NuevaCantidad_disponible);
     
     public void deleteProducto (Long id_producto);
     
     public void actualizarStock (DetalleVentaDTO detalle);
     
     public void devolverStockdeVenta (Venta venta);
     
     public List <Producto> getProductosConStockMenorA5 ();
     
     public void crearProducto (ProductoDTO productoDTO);
    
}
