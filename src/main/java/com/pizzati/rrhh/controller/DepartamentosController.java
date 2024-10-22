package com.pizzati.rrhh.controller;

import com.pizzati.rrhh.dto.DepartamentoListado;
import com.pizzati.rrhh.entity.Departamento;
import com.pizzati.rrhh.service.DepartamentoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/departamento")
public class DepartamentosController {
    private final DepartamentoService departamentoService;

    public DepartamentosController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping()
    public ModelAndView listadoDepartamentos(
            HttpServletRequest request
    ){
        Integer sucursal = (Integer) request.getSession().getAttribute("sucursal");
        if (sucursal == null) {
            return new ModelAndView("redirect:/sucursales");
        }
        ModelAndView mav = new ModelAndView("./departamento/departamento-list");

        List<DepartamentoListado> departamentos = departamentoService.findAll()
                .stream()
                .map(d->new DepartamentoListado(d.getId(),d.getNombreDepartamento()))
                .toList();

        mav.addObject("departamentos",departamentos);
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarDepartamento(
            @PathVariable int id,
            HttpServletRequest request
    ){
        Integer sucursal = (Integer) request.getSession().getAttribute("sucursal");
        if (sucursal == null) {
            return new ModelAndView("redirect:/sucursales");
        }
        Departamento departamento = departamentoService.findById(id);
        ModelAndView mav = new ModelAndView("./departamento/departamento-editar");
        mav.addObject("departamento",departamento);
        return mav;
    }
}
