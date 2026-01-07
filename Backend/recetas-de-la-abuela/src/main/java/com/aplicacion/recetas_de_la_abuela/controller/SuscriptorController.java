/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.controller;

import com.aplicacion.recetas_de_la_abuela.DTO.SuscriptorDTO;
import com.aplicacion.recetas_de_la_abuela.model.Suscriptor;
import com.aplicacion.recetas_de_la_abuela.service.ISuscriptorService;
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
public class SuscriptorController {
    
    @Autowired
     private ISuscriptorService suscriptorServ;
    
    @PostMapping("/suscriptor/crear")
    public String saveCliente (@RequestBody SuscriptorDTO suscriptorDTO) {
        suscriptorServ.generarSuscriptor(suscriptorDTO);
        
        return "Suscriptor creado correctamente";
    }
    
    @GetMapping("/suscriptor/{id_suscriptor}")
    public Suscriptor findSuscriptor (@PathVariable Long id_suscriptor) {
        return suscriptorServ.findSuscriptor(id_suscriptor);
    }
    
    @GetMapping("/suscriptores")
    public List<Suscriptor> getSuscriptores() {
        return suscriptorServ.getSuscriptores();
    }
    
     @PutMapping("/suscriptor/editar/{id_suscriptor}")
    public Suscriptor editSuscriptor(@PathVariable Long id_cliente,
                                    @RequestParam (required = false) String nuevoNombre,
                                    @RequestParam (required = false) String nuevoApellido,
                                    @RequestParam (required = false) LocalDate nuevaFecha_nacimiento,
                                    @RequestParam (required = false) String nuevaContrasena,
                                    @RequestParam (required = false) Long nuevoCelular,
                                    @RequestParam (required = false) String nuevoCorreo_electronico,
                                    @RequestParam (required = false) String nuevaProvincia,
                                    @RequestParam (required = false) String nuevaCiudad,
                                    @RequestParam (required = false) String nuevaDireccion,
                                    @RequestParam (required = false) String nuevaPreferenciaDeReceta,
                                    @RequestParam (required = false) Boolean nuevoRecibir_ofertas) {
        return suscriptorServ.editSuscriptor(id_cliente, nuevoNombre, nuevoApellido, nuevaFecha_nacimiento, nuevaContrasena, nuevoCelular, nuevoCorreo_electronico,
                                                nuevaProvincia, nuevaCiudad, nuevaDireccion, nuevaPreferenciaDeReceta, nuevoRecibir_ofertas);
    }
    
    @DeleteMapping("/suscriptor/eliminar/{id_suscriptor}")
    public String deleteSuscriptor(@PathVariable Long id_suscriptor) {
        suscriptorServ.deleteSuscriptor(id_suscriptor);
        return "Suscriptor eliminado exitosamente";
    }
    
}
