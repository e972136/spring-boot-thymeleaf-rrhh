package com.pizzati.rrhh.controller;

import com.pizzati.rrhh.dto.DepartamentoListado;
import com.pizzati.rrhh.entity.Puesto;
import com.pizzati.rrhh.service.PuestoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/puesto")
public class PuestoController {

    private final PuestoService puestoService;

    public PuestoController(PuestoService puestoService) {
        this.puestoService = puestoService;
    }

    @GetMapping()
    public ModelAndView listadoPuestos(
            HttpServletRequest request
    ){
        Integer sucursal = (Integer) request.getSession().getAttribute("sucursal");
        if (sucursal == null) {
            return new ModelAndView("redirect:/sucursales");
        }

        ModelAndView mav = new ModelAndView("./puesto/puesto-list");

        List<DepartamentoListado> puestos = puestoService.findAll()        .stream()
                .map(d->new DepartamentoListado(d.getId(),d.getNombrePuesto()))
                .toList();
        mav.addObject("puestos",puestos);
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarPuesto(
            @PathVariable int id,
            HttpServletRequest request
    ){
        Integer sucursal = (Integer) request.getSession().getAttribute("sucursal");
        if (sucursal == null) {
            return new ModelAndView("redirect:/sucursales");
        }
        Puesto puesto = puestoService.findById(id);
        ModelAndView mav = new ModelAndView("./puesto/puesto-editar");
        mav.addObject("puesto",puesto);
        return mav;
    }
}
