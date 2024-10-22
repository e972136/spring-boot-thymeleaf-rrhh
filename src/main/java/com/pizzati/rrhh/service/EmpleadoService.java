package com.pizzati.rrhh.service;

import com.pizzati.rrhh.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmpleadoService {
    Page<Empleado> allEmpleadosSucursal(int sucursalId, Pageable page);
    Empleado getEmpleado(int idEmpleado);
    Empleado saveEmpleado(Empleado empleado);

    Empleado updateEmpleado(Empleado empleado);
}
