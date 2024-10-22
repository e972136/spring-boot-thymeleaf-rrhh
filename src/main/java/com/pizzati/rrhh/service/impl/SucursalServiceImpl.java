package com.pizzati.rrhh.service.impl;

import com.pizzati.rrhh.entity.Sucursal;
import com.pizzati.rrhh.repository.SucursalRepository;
import com.pizzati.rrhh.service.SucursalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalServiceImpl(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal findById(int sucursal) {
        return sucursalRepository.findById(sucursal).orElse(null);
    }
}
