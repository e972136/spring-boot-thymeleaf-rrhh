package com.pizzati.rrhh.service.impl;

import com.pizzati.rrhh.entity.DescripcionDetalleEmpleado;
import com.pizzati.rrhh.repository.DescripcionDetalleEmpleadoRepository;
import com.pizzati.rrhh.service.DescripcionDetalleEmpleadoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescripcionDetalleEmpleadoServiceImpl implements DescripcionDetalleEmpleadoService {

    private final DescripcionDetalleEmpleadoRepository descripcionDetalleEmpleadoRepository;

    public DescripcionDetalleEmpleadoServiceImpl(DescripcionDetalleEmpleadoRepository descripcionDetalleEmpleadoRepository) {
        this.descripcionDetalleEmpleadoRepository = descripcionDetalleEmpleadoRepository;
    }

    @Override
    public List<DescripcionDetalleEmpleado> findAllByEmpleadoId(int idEmpleado) {
        return descripcionDetalleEmpleadoRepository.findAllByEmpleadoId(idEmpleado);
    }

    @Override
    public DescripcionDetalleEmpleado guardarNuevo(DescripcionDetalleEmpleado entity) {
        return descripcionDetalleEmpleadoRepository.save(entity);
    }
}
