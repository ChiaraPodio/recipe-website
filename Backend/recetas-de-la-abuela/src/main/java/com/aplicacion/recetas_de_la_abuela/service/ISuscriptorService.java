/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.service;

import com.aplicacion.recetas_de_la_abuela.DTO.SuscriptorDTO;
import com.aplicacion.recetas_de_la_abuela.model.Suscriptor;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author chiarapodio
 */
@Service
public interface ISuscriptorService {
    
    public void saveSuscriptor (Suscriptor suscriptor);
    
     public Suscriptor findSuscriptor (Long id_suscriptor);
     
     public List<Suscriptor> getSuscriptores();
     
     public Suscriptor editSuscriptor (Long id_suscriptor, String nuevoNombre, String nuevoApellido, LocalDate nuevaFecha_nacimiento, String nuevaContrasena, Long nuevoCelular, String nuevoCorreo, String nuevaProvincia, String nuevaCiudad, String nuevaDireccion, String nuevaPreferenciaDeReceta, Boolean nuevoRecibir_ofertas);
     
     public void deleteSuscriptor (Long id_suscriptor);
     
     public void generarSuscriptor (SuscriptorDTO suscriptorDTO);
    
}
