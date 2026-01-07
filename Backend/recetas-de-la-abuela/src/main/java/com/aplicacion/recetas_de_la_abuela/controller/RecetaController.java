/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.controller;

import com.aplicacion.recetas_de_la_abuela.model.Ingrediente;
import com.aplicacion.recetas_de_la_abuela.model.PasoReceta;
import com.aplicacion.recetas_de_la_abuela.model.Receta;
import com.aplicacion.recetas_de_la_abuela.service.IRecetaService;
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
public class RecetaController {
    
    @Autowired
    private IRecetaService recetaServ;
    
        @PostMapping("/receta/crear")
    public String saveReceta (@RequestBody Receta receta) {
        recetaServ.saveReceta(receta);
        
        return "Receta creada correctamente";
    }
    
    @GetMapping("/receta/{codigo_receta}")
    public Receta findReceta (@PathVariable Long codigo_receta) {
        return recetaServ.findReceta(codigo_receta);
    }
    
    @GetMapping("/recetas")
    public List<Receta> getRecetas() {
        return recetaServ.getRecetas();
    }
    
    @PutMapping("/recetas/editar/{codigo_receta}")
    public Receta editReceta(@PathVariable Long codigo_receta,
                                    @RequestParam (required = false) String nuevoNombre,
                                    @RequestParam (required = false) String nuevaDescripcion,
                                    @RequestParam (required = false) String nuevoTiempo_preparacion,
                                    @RequestParam (required = false) String nuevaCategoria,
                                    @RequestParam (required = false) List<Ingrediente> nuevaListaingredientes,
                                    @RequestParam (required = false) List <PasoReceta> nuevaListaPasos) {
        return recetaServ.editReceta(codigo_receta, nuevoNombre, nuevaDescripcion, nuevoTiempo_preparacion, nuevaCategoria,
                nuevaListaingredientes,nuevaListaPasos);
        
    }
    
    @DeleteMapping("/recetas/eliminar/{codigo_receta}")
    public String deleteReceta(@PathVariable Long codigo_receta) {
        recetaServ.deleteReceta(codigo_receta);
        return "Receta eliminada exitosamente";
    }
    
}
