package com.pizzati.rrhh.service.impl;

import com.pizzati.rrhh.entity.DescripcionDetalle;
import com.pizzati.rrhh.repository.DescripcionDetalleRepository;
import com.pizzati.rrhh.service.DescripcionDetalleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DescripcionDetalleServiceImpl implements DescripcionDetalleService {

    private final DescripcionDetalleRepository descripcionDetalleRepository;

    public DescripcionDetalleServiceImpl(DescripcionDetalleRepository descripcionDetalleRepository) {
        this.descripcionDetalleRepository = descripcionDetalleRepository;
    }

    @Override
    public Page<DescripcionDetalle> findAll(Pageable page) {
        return descripcionDetalleRepository.findAll(page);
    }

    @Override
    public DescripcionDetalle save(DescripcionDetalle descripcionDetalle) {
        return descripcionDetalleRepository.save(descripcionDetalle);
    }

    @Override
    public List<DescripcionDetalle> findAllByObligatorio(boolean obligatorio) {
        return descripcionDetalleRepository.findAllByObligatoria(obligatorio);
    }

    @Override
    public List<DescripcionDetalle> findAllSet() {
        return descripcionDetalleRepository.findAll();
    }
}
