package com.pizzati.rrhh.service;

import com.pizzati.rrhh.entity.Puesto;

import java.util.List;

public interface PuestoService {
    List<Puesto> findAll();

    Puesto findById(int id);

    Puesto findByNombrePuestoAndDescripcionPuesto(String nombrePuesto, String descripcionPuesto);

    Puesto save(Puesto objetoPuesto);
}
