package com.pizzati.rrhh.service;

import com.pizzati.rrhh.entity.Departamento;

import java.util.List;

public interface DepartamentoService {
    List<Departamento> findAll();

    Departamento findById(int id);
}
