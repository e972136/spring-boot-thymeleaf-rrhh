package com.pizzati.rrhh.controller;

import com.pizzati.rrhh.dto.DepartamentoListado;
import com.pizzati.rrhh.dto.PuestoListado;
import com.pizzati.rrhh.entity.Puesto;
import com.pizzati.rrhh.service.PuestoService;
import com.pizzati.rrhh.utilities.TipoMensaje;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.pizzati.rrhh.utilities.MetodosGenerales.mensajeErrorDetalle;
import static java.util.Objects.isNull;

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

        List<PuestoListado> puestos = puestoService.findAll()        .stream()
                .map(d->new PuestoListado(d.getId(),d.getNombrePuesto(),d.getDescripcionPuesto()))
                .toList();


        mav.addObject("puestos",puestos);
        mav.addObject("objetoPuesto",new Puesto());
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

    @PostMapping("/guardar")
    public ModelAndView crearNuevo(
        Puesto objetoPuesto,
        RedirectAttributes redirectAttrs
    ){
        Puesto db = puestoService.findByNombrePuestoAndDescripcionPuesto(objetoPuesto.getNombrePuesto(),objetoPuesto.getDescripcionPuesto());

        if(isNull(db)){
            db = puestoService.save(objetoPuesto);
            mensajeErrorDetalle(redirectAttrs,"Guardado "+db.getNombrePuesto() + "!", TipoMensaje.WARNING);
        }else{
            mensajeErrorDetalle(redirectAttrs, "Departamento ya existe!",TipoMensaje.WARNING);
        }

        ModelAndView mav = new ModelAndView("redirect:/puesto");
        return mav;
    }

    @PostMapping("/actualizar")
    public ModelAndView actualizarPuesto(
        Puesto objetoPuesto,
        RedirectAttributes redirectAttrs
    ){
        Puesto db = puestoService.findByNombrePuestoAndDescripcionPuesto(objetoPuesto.getNombrePuesto(),objetoPuesto.getDescripcionPuesto());

        if(isNull(db)){
            db = puestoService.save(objetoPuesto);
            mensajeErrorDetalle(redirectAttrs,"Guardado "+db.getNombrePuesto() + "!", TipoMensaje.WARNING);
        }else{
            mensajeErrorDetalle(redirectAttrs, "Puesto y descripcion ya existe!",TipoMensaje.WARNING);
        }

        ModelAndView mav = new ModelAndView("redirect:/puesto");
        return mav;
    }
}
