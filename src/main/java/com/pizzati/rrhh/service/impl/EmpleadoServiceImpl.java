package com.pizzati.rrhh.service.impl;

import com.pizzati.rrhh.entity.DescripcionDetalle;
import com.pizzati.rrhh.entity.DescripcionDetalleEmpleado;
import com.pizzati.rrhh.entity.Empleado;
import com.pizzati.rrhh.repository.EmpleadoRepository;
import com.pizzati.rrhh.service.DescripcionDetalleEmpleadoService;
import com.pizzati.rrhh.service.DescripcionDetalleService;
import com.pizzati.rrhh.service.EmpleadoService;
import com.pizzati.rrhh.utilities.TipoElemento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final DescripcionDetalleEmpleadoService descripcionDetalleEmpleadoService;

    private final DescripcionDetalleService descripcionDetalleService;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, DescripcionDetalleEmpleadoService empleadoService, DescripcionDetalleService descripcionDetalleService) {
        this.empleadoRepository = empleadoRepository;
        this.descripcionDetalleEmpleadoService = empleadoService;
        this.descripcionDetalleService = descripcionDetalleService;
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
        Empleado save = empleadoRepository.save(empleado);

        List<DescripcionDetalle> allByObligatorio = descripcionDetalleService.findAllByObligatorio(true);

        for(DescripcionDetalle x:allByObligatorio){

            descripcionDetalleEmpleadoService.guardarNuevo(
                    new DescripcionDetalleEmpleado(
                            0,
                            save.getId(),
                            x.getId(),
                            x.getQuincenaAsignada(),
                            TipoElemento.DEDUCCION,
                            x.getMonto(),
                            null,
                            null,
                            true
                    )
            );

        }


        return save;
    }

    @Override
    public Empleado updateEmpleado(Empleado empleado) {
        return null;
    }
}
