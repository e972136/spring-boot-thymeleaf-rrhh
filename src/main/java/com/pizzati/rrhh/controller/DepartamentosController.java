package com.pizzati.rrhh.controller;

import com.pizzati.rrhh.dto.DepartamentoListado;
import com.pizzati.rrhh.entity.Departamento;
import com.pizzati.rrhh.service.DepartamentoService;
import com.pizzati.rrhh.utilities.TipoMensaje;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.pizzati.rrhh.utilities.MetodosGenerales.mensajeErrorDetalle;
import static java.util.Objects.isNull;

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
        mav.addObject("objetoDepartamento",new Departamento());
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

    @PostMapping("/guardar")
    public ModelAndView crearNuevo(
            Departamento objetoDepartamento,
            HttpServletRequest request,
            RedirectAttributes redirectAttrs
    ){
        Departamento bd =  departamentoService.findByNombreDepartamento(objetoDepartamento.getNombreDepartamento());

        if(isNull(bd)){
            bd = departamentoService.save(objetoDepartamento);
            mensajeErrorDetalle(redirectAttrs,"Guardado "+bd.getNombreDepartamento() + "!",TipoMensaje.WARNING);
        }else{
            mensajeErrorDetalle(redirectAttrs, "Departamento ya existe!",TipoMensaje.WARNING);
        }

        ModelAndView mav = new ModelAndView("redirect:/departamento");
        return mav;
    }

    @PostMapping("/actualizar")
    public ModelAndView actualizarDepartamento(
            Departamento objetoDepartamento,
            HttpServletRequest request,
            RedirectAttributes redirectAttrs
    ){
        Departamento bd =  departamentoService.findByNombreDepartamento(objetoDepartamento.getNombreDepartamento());

        if(isNull(bd)){
            bd = departamentoService.save(objetoDepartamento);
            mensajeErrorDetalle(redirectAttrs,"Guardado "+bd.getNombreDepartamento() + "!",TipoMensaje.WARNING);
        }else{
            mensajeErrorDetalle(redirectAttrs, "Departamento ya existe!",TipoMensaje.WARNING);
        }

        ModelAndView mav = new ModelAndView("redirect:/departamento");
        return mav;
    }


}
