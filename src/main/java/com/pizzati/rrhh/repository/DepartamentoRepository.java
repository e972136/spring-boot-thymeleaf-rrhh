package com.pizzati.rrhh.repository;

import com.pizzati.rrhh.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento,Integer> {
    Departamento findByNombreDepartamento(String nombreDepartamento);
}
