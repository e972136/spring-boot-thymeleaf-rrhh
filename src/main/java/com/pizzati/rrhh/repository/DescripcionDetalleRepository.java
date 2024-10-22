package com.pizzati.rrhh.repository;

import com.pizzati.rrhh.entity.DescripcionDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DescripcionDetalleRepository extends JpaRepository<DescripcionDetalle,Integer> {
    List<DescripcionDetalle> findAllByObligatoria(boolean obligatoria);
}
