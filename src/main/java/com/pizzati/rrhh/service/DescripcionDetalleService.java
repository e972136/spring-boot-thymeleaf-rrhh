package com.pizzati.rrhh.service;

import com.pizzati.rrhh.entity.DescripcionDetalle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface DescripcionDetalleService {
    Page<DescripcionDetalle> findAll(Pageable page);

    DescripcionDetalle save(DescripcionDetalle descripcionDetalle);

    List<DescripcionDetalle> findAllByObligatorio(boolean obligatorio);

    List<DescripcionDetalle> findAllSet();
}
