package com.pizzati.rrhh.service.impl;

import com.pizzati.rrhh.entity.Departamento;
import com.pizzati.rrhh.repository.DepartamentoRepository;
import com.pizzati.rrhh.service.DepartamentoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    @Override
    public Departamento findById(int id) {
        return departamentoRepository.findById(id).orElse(null);
    }
}
