package com.pizzati.rrhh.service;

import com.pizzati.rrhh.entity.DescripcionDetalleEmpleado;

import java.util.List;

public interface DescripcionDetalleEmpleadoService {
    List<DescripcionDetalleEmpleado> findAllByEmpleadoId(int idEmpleado);

    DescripcionDetalleEmpleado guardarNuevo(DescripcionDetalleEmpleado entity);
}
