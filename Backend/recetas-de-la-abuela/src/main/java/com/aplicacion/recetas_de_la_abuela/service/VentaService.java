/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.service;

import com.aplicacion.recetas_de_la_abuela.DTO.DetalleVentaDTO;
import com.aplicacion.recetas_de_la_abuela.DTO.VentaDTO;
import com.aplicacion.recetas_de_la_abuela.model.DetalleVenta;
import com.aplicacion.recetas_de_la_abuela.model.Producto;
import com.aplicacion.recetas_de_la_abuela.model.Suscriptor;
import com.aplicacion.recetas_de_la_abuela.model.Venta;
import com.aplicacion.recetas_de_la_abuela.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chiarapodio
 */
@Service
public class VentaService implements IVentaService {
    
    @Autowired
    private IVentaRepository ventaRepo; 
    
     @Autowired
    private IProductoService productoServ;
     
     @Autowired
    private ISuscriptorService suscriptorServ;

    @Override
    public void saveVenta(Venta venta) {
        ventaRepo.save(venta);
    }

    @Override
    public Venta findVenta(Long codigo_venta) {
        Venta venta = ventaRepo.findById(codigo_venta).orElse(null);
        return venta;
    }

    @Override
    public List<Venta> getVentas() {
        List<Venta> listaVentas = ventaRepo.findAll();
        return listaVentas;
    }

@Override
public Venta editVenta(Long codigo_venta, LocalDate nuevaFecha_venta, Long idSuscriptorNuevo, List<DetalleVentaDTO> listaDetalleNueva) {
    Venta venta = this.findVenta(codigo_venta);
    

    if (nuevaFecha_venta != null) {
    venta.setFecha_venta(nuevaFecha_venta);}
    if (idSuscriptorNuevo != null) {
    Suscriptor suscriptorNuevo = suscriptorServ.findSuscriptor(idSuscriptorNuevo); 
    venta.setUnSuscriptor(suscriptorNuevo);}
    
    if (listaDetalleNueva != null) {
     productoServ.devolverStockdeVenta(venta);
     
     venta.setListaDetalleVenta(this.crearDetalle(venta, listaDetalleNueva));
    
    }  
    
    this.saveVenta(venta);
    return venta;
}

    @Override
    public void deleteVenta(Long codigo_venta) {
        ventaRepo.deleteById(codigo_venta);
    }
    
    @Override
     public List<DetalleVenta> crearDetalle (Venta venta, List<DetalleVentaDTO> detalleVentaDTO) {
         
         List<DetalleVenta> listaDetalle = new ArrayList<>();
         
         for (int i=0; i < detalleVentaDTO.size(); i++) { 
             DetalleVenta detalle = new DetalleVenta();
             DetalleVentaDTO detVen = detalleVentaDTO.get(i); 
             Producto producto = productoServ.findProducto(detVen.getIdProducto());
             
             detalle.setVenta(venta); 
             detalle.setProducto(producto);
             detalle.setCantidad(detVen.getCantidad());
             detalle.setPrecioUnitarioVenta(producto.getPrecioUnitarioActual());
             detalle.setSubtotal(detalle.getPrecioUnitarioVenta()*detalle.getCantidad());
             productoServ.actualizarStock(detVen);
             listaDetalle.add(detalle);
         }
         
         
         return listaDetalle;

     }
     
     @Override
     public void crearVenta (VentaDTO ventaDto) {
         
         Venta venta = new Venta();
         Suscriptor suscriptor = suscriptorServ.findSuscriptor(ventaDto.getIdSuscriptor());
         
         venta.setFecha_venta(LocalDate.now());
         venta.setListaDetalleVenta(this.crearDetalle(venta, ventaDto.getDetalleVenta()));
         venta.setUnSuscriptor(suscriptor);
         venta.setTotal(this.calcularTotal(venta.getListaDetalleVenta()));
         
         suscriptor.getListaVentasSuscriptor().add(venta);
         
         this.saveVenta(venta);
     }
    
    
    @Override
    public Double calcularTotal (List <DetalleVenta> listaDetalles) {
        Double total = 0.0;
        
        for (DetalleVenta pasoDetalle : listaDetalles) {
            total+= pasoDetalle.getSubtotal();
        }
        return total;
    }
    
    @Override
    public List<Producto> getProductosDeVenta(Long codigo_venta) {
        Venta venta = this.findVenta(codigo_venta);
        List<Producto> listaProductos = new ArrayList<>();
        
        for (DetalleVenta detalle : venta.getListaDetalleVenta()) {
            listaProductos.add(detalle.getProducto());
        }
        return listaProductos;
    }
    
    @Override
    public String getTotalMontoYVentas (LocalDate fecha_venta) {
        List<Venta> listaVentas = this.getVentas();
        
        Double montoTotal=0.0;
        Integer cantTotal=0;
        
        for (Venta pasoVenta : listaVentas) {
            if (pasoVenta.getFecha_venta().equals(fecha_venta)) {
                montoTotal+=pasoVenta.getTotal();
                cantTotal+=1;
            }
        }
        return String.format("En el dia hubo %d ventas. El monto total fue de %.2f.", cantTotal, montoTotal);
    }
    
}
