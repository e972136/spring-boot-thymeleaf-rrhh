package com.pizzati.rrhh.repository;

import com.pizzati.rrhh.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
    Page<Empleado> findAllBySucursalId(int sucursalId, Pageable page);
}
