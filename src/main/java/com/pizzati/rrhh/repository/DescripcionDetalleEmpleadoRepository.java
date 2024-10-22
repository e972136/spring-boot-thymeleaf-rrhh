package com.pizzati.rrhh.repository;

import com.pizzati.rrhh.entity.DescripcionDetalleEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DescripcionDetalleEmpleadoRepository extends JpaRepository<DescripcionDetalleEmpleado,Integer> {
    List<DescripcionDetalleEmpleado> findAllByEmpleadoId(int idEmpleado);
}
