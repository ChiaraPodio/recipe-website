/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.service;

import com.aplicacion.recetas_de_la_abuela.model.Ingrediente;
import com.aplicacion.recetas_de_la_abuela.model.PasoReceta;
import com.aplicacion.recetas_de_la_abuela.model.Receta;
import com.aplicacion.recetas_de_la_abuela.repository.IRecetaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chiarapodio
 */
@Service
public class RecetaService implements IRecetaService{
    
     @Autowired
    private IRecetaRepository recetaRepo; 
     
     @Override
    public void saveReceta(Receta receta) {
        recetaRepo.save(receta);
    }

    @Override
    public Receta findReceta(Long codigo_receta) {
        Receta receta = recetaRepo.findById(codigo_receta).orElse(null);
        return receta;
    }

    @Override
    public List<Receta> getRecetas() {
        List<Receta> listaRecetas = recetaRepo.findAll();
        return listaRecetas;
        }

    @Override
    public Receta editReceta(Long codigo_receta, String nuevoNombre, String nuevaDescripcion, 
                                String nuevoTiempo_preparacion, String nuevaCategoria,
                                List<Ingrediente> nuevaListaingredientes, List <PasoReceta> nuevaListaPasos) {
        Receta receta = this.findReceta(codigo_receta);
        
        if (nuevoNombre != null) {
        receta.setNombre(nuevoNombre);}
        if (nuevaDescripcion != null) {
        receta.setDescripcion(nuevaDescripcion);}
        if (nuevoTiempo_preparacion != null) {
        receta.setTiempo_preparacion(nuevoTiempo_preparacion);}
        if (nuevaCategoria != null) {
        receta.setCategoria(nuevaCategoria);}
        if (! nuevaListaingredientes.isEmpty()) {
        receta.setListaIngredientes(nuevaListaingredientes);}
        if (! nuevaListaPasos.isEmpty()) {
        receta.setListaPasosReceta(nuevaListaPasos);}
        
        
        this.saveReceta(receta);
        
        return receta;
    }

    @Override
    public void deleteReceta(Long codigo_receta) {
        recetaRepo.deleteById(codigo_receta);
    }
    
}
