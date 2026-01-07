/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.service;

import com.aplicacion.recetas_de_la_abuela.model.Ingrediente;
import com.aplicacion.recetas_de_la_abuela.model.PasoReceta;
import com.aplicacion.recetas_de_la_abuela.model.Receta;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author chiarapodio
 */
@Service
public interface IRecetaService {
    
    public void saveReceta(Receta cliente);
    
    public Receta findReceta(Long codigo_receta);
    
    public List<Receta> getRecetas();
    
    public Receta editReceta(Long codigo_receta, String nuevoNombre, String nuevaDescripcion, 
                                String nuevoTiempo_preparacion, String nuevaCategoria,
                                List<Ingrediente> nuevaListaingredientes, List <PasoReceta> nuevaListaPasos);
    
    public void deleteReceta(Long codigo_receta);
    
}
