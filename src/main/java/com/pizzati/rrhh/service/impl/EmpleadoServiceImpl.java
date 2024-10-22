package com.pizzati.rrhh.service.impl;

import com.pizzati.rrhh.entity.Empleado;
import com.pizzati.rrhh.repository.EmpleadoRepository;
import com.pizzati.rrhh.service.EmpleadoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Page<Empleado> allEmpleadosSucursal(int sucursalId, Pageable page) {
        return empleadoRepository.findAllBySucursalId(sucursalId,page);
    }

    @Override
    public Empleado getEmpleado(int idEmpleado) {
        return empleadoRepository.findById(idEmpleado).orElse(null);
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado updateEmpleado(Empleado empleado) {
        return null;
    }
}
