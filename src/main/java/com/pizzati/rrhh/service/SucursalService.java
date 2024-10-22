package com.pizzati.rrhh.service;

import com.pizzati.rrhh.entity.Sucursal;

import java.util.List;

public interface SucursalService {
    List<Sucursal> findAll();

    Sucursal findById(int sucursal);
}
