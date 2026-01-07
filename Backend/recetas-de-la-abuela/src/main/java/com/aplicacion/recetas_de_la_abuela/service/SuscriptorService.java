/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.service;

import com.aplicacion.recetas_de_la_abuela.DTO.SuscriptorDTO;
import com.aplicacion.recetas_de_la_abuela.model.Suscriptor;
import com.aplicacion.recetas_de_la_abuela.model.Venta;
import com.aplicacion.recetas_de_la_abuela.repository.ISuscriptorRepository;
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
public class SuscriptorService implements ISuscriptorService {
    
    @Autowired
    private ISuscriptorRepository suscriptorRepo; 

    @Override
    public void saveSuscriptor(Suscriptor suscriptor) {
        suscriptorRepo.save(suscriptor);
    }

    @Override
    public Suscriptor findSuscriptor(Long id_suscriptor) {
        Suscriptor suscriptor = suscriptorRepo.findById(id_suscriptor).orElse(null);
        return suscriptor;
    }

    @Override
    public List<Suscriptor> getSuscriptores() {
        List<Suscriptor> listaSuscriptores = suscriptorRepo.findAll();
        return listaSuscriptores;
        }

    @Override
    public Suscriptor editSuscriptor(Long id_suscriptor, String nuevoNombre, String nuevoApellido, LocalDate nuevaFecha_nacimiento, String nuevaContrasena, Long nuevoCelular, String nuevoCorreo, String nuevaProvincia, String nuevaCiudad, String nuevaDireccion, String nuevaPreferenciaDeReceta, Boolean nuevoRecibir_ofertas) {
        Suscriptor suscriptor = this.findSuscriptor(id_suscriptor);
        
        if (nuevoNombre != null) {
        suscriptor.setNombre(nuevoNombre);}
        if (nuevoApellido != null) {
        suscriptor.setApellido(nuevoApellido);}
        if (nuevaFecha_nacimiento != null) {
        suscriptor.setFecha_nacimiento(nuevaFecha_nacimiento);}
        if (nuevaContrasena != null) {
        suscriptor.setContrasena(nuevaContrasena);}
        if (nuevoCelular != null) {
        suscriptor.setCelular(nuevoCelular);}
        if (nuevoCorreo != null) {
        suscriptor.setCorreo_electronico(nuevoCorreo);
        }
        if (nuevaProvincia != null) {
        suscriptor.setProvincia(nuevaProvincia);
        }
        if (nuevaCiudad != null) {
        suscriptor.setCiudad(nuevaCiudad);
        }
        if (nuevaDireccion != null) {
        suscriptor.setDireccion(nuevaDireccion);
        }
        if (nuevaPreferenciaDeReceta != null) {
        suscriptor.setPreferenciaDeReceta(nuevaPreferenciaDeReceta);
        }
        if (nuevoRecibir_ofertas != null) {
        suscriptor.setRecibir_ofertas(nuevoRecibir_ofertas);
        }
        
        this.saveSuscriptor(suscriptor);
        
        return suscriptor;
    }

    @Override
    public void deleteSuscriptor(Long id_suscriptor) {
        suscriptorRepo.deleteById(id_suscriptor);
    }
    
    @Override
    public void generarSuscriptor (SuscriptorDTO suscriptorDTO) {
        Suscriptor suscriptor = new Suscriptor();
        List <Venta> listaVentasSuscriptor = new ArrayList<>();
        
        suscriptor.setNombre(suscriptorDTO.getNombre());
        suscriptor.setApellido(suscriptorDTO.getApellido());
        suscriptor.setFecha_nacimiento(suscriptorDTO.getFecha_nacimiento());
        suscriptor.setContrasena(suscriptorDTO.getContrasena());
        suscriptor.setCelular(suscriptorDTO.getCelular());
        suscriptor.setCorreo_electronico(suscriptorDTO.getCorreo_electronico());
        suscriptor.setProvincia(suscriptorDTO.getProvincia());
        suscriptor.setCiudad(suscriptorDTO.getCiudad());
        suscriptor.setDireccion(suscriptorDTO.getDireccion());
        suscriptor.setPreferenciaDeReceta(suscriptorDTO.getPreferenciaDeReceta());
        suscriptor.setRecibir_ofertas(suscriptorDTO.getRecibir_ofertas());
        suscriptor.setListaVentasSuscriptor(listaVentasSuscriptor);
        
        this.saveSuscriptor(suscriptor);
        
    }
    
}
