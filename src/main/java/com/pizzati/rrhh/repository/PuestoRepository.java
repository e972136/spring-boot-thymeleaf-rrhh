package com.pizzati.rrhh.repository;

import com.pizzati.rrhh.entity.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuestoRepository extends JpaRepository<Puesto,Integer> {
    Puesto findByNombrePuestoAndDescripcionPuesto(String nombrePuesto, String descripcionPuesto);
}
