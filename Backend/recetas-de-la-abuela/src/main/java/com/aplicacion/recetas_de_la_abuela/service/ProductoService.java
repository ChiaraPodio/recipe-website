/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.service;

import com.aplicacion.recetas_de_la_abuela.DTO.DetalleVentaDTO;
import com.aplicacion.recetas_de_la_abuela.DTO.ProductoDTO;
import com.aplicacion.recetas_de_la_abuela.model.DetalleVenta;
import com.aplicacion.recetas_de_la_abuela.model.Producto;
import com.aplicacion.recetas_de_la_abuela.model.Venta;
import com.aplicacion.recetas_de_la_abuela.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chiarapodio
 */
@Service
public class ProductoService implements IProductoService {
    
    @Autowired
    private IProductoRepository productoRepo; 

    @Override
    public void saveProducto(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public Producto findProducto(Long codigo_producto) {
        Producto producto = productoRepo.findById(codigo_producto).orElse(null); 
        return producto;
    }

    @Override
    public List<Producto> getProductos() {
        List<Producto> listaProductos = productoRepo.findAll();
        return listaProductos;
    }

    @Override
    public Producto editProducto(Long codigo_producto, String nuevoNombre, String nuevaDescripcion, String nuevaMarca, Double nuevoPrecioUnitarioActual, Double nuevoStock) {
        Producto producto = this.findProducto(codigo_producto);
        
        if (nuevoNombre != null) {
        producto.setNombre(nuevoNombre);}
        if (nuevaDescripcion != null) {
        producto.setDescripcion(nuevaDescripcion);}
        if (nuevaMarca != null) {
        producto.setMarca(nuevaMarca);}
        if (nuevoPrecioUnitarioActual != null) {
        producto.setPrecioUnitarioActual(nuevoPrecioUnitarioActual);}
        if (nuevoStock != null) {
        producto.setStock(nuevoStock);}
        
        this.saveProducto(producto);
        
        return producto;
    }

    @Override
    public void deleteProducto(Long codigo_producto) {
        productoRepo.deleteById(codigo_producto);
    }
    
    @Override
    public void actualizarStock (DetalleVentaDTO detalle) {
         
        Producto producto = this.findProducto(detalle.getIdProducto());
        
        producto.setStock(producto.getStock()-detalle.getCantidad());
             
             this.saveProducto(producto);
             }
    
    @Override
    public void devolverStockdeVenta (Venta venta) {
        
        List <DetalleVenta> listaDetallesAnterior = venta.getListaDetalleVenta();
        
        for (DetalleVenta pasoDetVen : listaDetallesAnterior) {
            Producto producto = pasoDetVen.getProducto();
            producto.setStock(producto.getStock()+pasoDetVen.getCantidad());
            this.saveProducto(producto);
        }
    }
    
    @Override
    public List <Producto> getProductosConStockMenorA5 () {
        
        List <Producto> listaProductos = this.getProductos();
        List <Producto> listaProductosBajoStock = new ArrayList<>();
        
        for (Producto pasoProducto : listaProductos) {
            if (pasoProducto.getStock()<5) {
                listaProductosBajoStock.add(pasoProducto);
            }
        }
        return listaProductosBajoStock;
        
    }
    
    @Override
    public void crearProducto (ProductoDTO productoDTO) {
        Producto producto = new Producto();
        List <DetalleVenta> detallesEnLosQueEstaProducto = new ArrayList<>();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setMarca(productoDTO.getMarca());
        producto.setStock(productoDTO.getStock());
        producto.setPrecioUnitarioActual(productoDTO.getPrecioUnitarioActual());
        producto.setDetallesEnLosQueEstaProducto(detallesEnLosQueEstaProducto);
        
        this.saveProducto(producto);
    }
        
    
}
