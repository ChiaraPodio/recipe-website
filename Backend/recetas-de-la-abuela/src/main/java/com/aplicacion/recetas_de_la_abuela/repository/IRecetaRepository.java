/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplicacion.recetas_de_la_abuela.repository;

import com.aplicacion.recetas_de_la_abuela.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chiarapodio
 */
@Repository
public interface IRecetaRepository extends JpaRepository <Receta, Long>{
    
}
