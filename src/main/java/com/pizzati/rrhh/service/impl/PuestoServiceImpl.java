package com.pizzati.rrhh.service.impl;

import com.pizzati.rrhh.entity.Puesto;
import com.pizzati.rrhh.repository.PuestoRepository;
import com.pizzati.rrhh.service.PuestoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoServiceImpl implements PuestoService {
    private final PuestoRepository puestoRepository;

    public PuestoServiceImpl(PuestoRepository puestoRepository) {
        this.puestoRepository = puestoRepository;
    }

    @Override
    public List<Puesto> findAll() {
        return puestoRepository.findAll();
    }

    @Override
    public Puesto findById(int id) {
        return puestoRepository.findById(id).orElse(null);
    }

    @Override
    public Puesto findByNombrePuestoAndDescripcionPuesto(String nombrePuesto, String descripcionPuesto) {
        return puestoRepository.findByNombrePuestoAndDescripcionPuesto(nombrePuesto,descripcionPuesto);
    }

    @Override
    public Puesto save(Puesto objetoPuesto) {
        return puestoRepository.save(objetoPuesto);
    }
}
