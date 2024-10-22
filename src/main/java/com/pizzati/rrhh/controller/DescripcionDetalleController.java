package com.pizzati.rrhh.controller;


import com.pizzati.rrhh.dto.DescripcionDetalleEmpleadoNuevo;
import com.pizzati.rrhh.dto.DescripcionDetalleNuevo;
import com.pizzati.rrhh.entity.DescripcionDetalle;
import com.pizzati.rrhh.entity.DescripcionDetalleEmpleado;
import com.pizzati.rrhh.entity.Empleado;
import com.pizzati.rrhh.service.DescripcionDetalleEmpleadoService;
import com.pizzati.rrhh.service.DescripcionDetalleService;
import com.pizzati.rrhh.service.EmpleadoService;
import com.pizzati.rrhh.utilities.ItemComboBox;
import com.pizzati.rrhh.utilities.MetodosGenerales;
import com.pizzati.rrhh.utilities.QuincenaAsignada;
import com.pizzati.rrhh.utilities.TipoElemento;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/descripcion-detalle")
public class DescripcionDetalleController {

    private final DescripcionDetalleService descripcionDetalleService;

    private final DescripcionDetalleEmpleadoService descripcionDetalleEmpleadoService;
    private final EmpleadoService empleadoService;



    public DescripcionDetalleController(DescripcionDetalleService descripcionDetalleService, DescripcionDetalleEmpleadoService descripcionDetalleEmpleadoService, EmpleadoService empleadoService) {
        this.descripcionDetalleService = descripcionDetalleService;
        this.descripcionDetalleEmpleadoService = descripcionDetalleEmpleadoService;
        this.empleadoService = empleadoService;
    }

    @GetMapping()
    public ModelAndView listadoPuestos(
            @PageableDefault(size = 20,sort = "descripcion",direction = Sort.Direction.ASC) Pageable page,
            HttpServletRequest request
    ){
        Integer sucursal = (Integer) request.getSession().getAttribute("sucursal");
        if (sucursal == null) {
            return new ModelAndView("redirect:/sucursales");
        }
        ModelAndView mav = new ModelAndView("./descripciondetalle/descripcion-detalle-list");
        Page<DescripcionDetalle> descripciones = descripcionDetalleService.findAll(page);

        mav.addObject("descripciones",descripciones);
        return mav;
    }



    @GetMapping("/agregar")
    public ModelAndView agregarDescripcion(
            HttpServletRequest request
    ){
        Integer sucursal = (Integer) request.getSession().getAttribute("sucursal");
        if (sucursal == null) {
            return new ModelAndView("redirect:/sucursales");
        }
        ModelAndView mav = new ModelAndView("./descripciondetalle/descripcion-detalle-crear");
        List<ItemComboBox> quincena = Arrays.stream(QuincenaAsignada.values())
                .map(e -> new ItemComboBox(e.getVal(), e.name())).toList();

        mav.addObject("descripcionDetalle",new DescripcionDetalleNuevo(0, BigDecimal.ZERO,true,false));
        mav.addObject("quincena", quincena);

        return mav;
    }

    @PostMapping("/guardar/{id}")
    public ModelAndView guardarDescripcion(
            @PathVariable Long id,
            DescripcionDetalleNuevo descripcionDetalle,
            HttpServletRequest request
    ){
        DescripcionDetalle descripcionDetalle1 = descripcionDetalleService.save(descripcionDetalle.fromEntity());
        return new ModelAndView("redirect:/descripcion-detalle");
    }

    @PostMapping("/guardar/asignacion/{id}")
    public ModelAndView guadarAsignacionEmpleado(
            @PathVariable Long id,
            DescripcionDetalleEmpleadoNuevo descripcionDetalleEmpleadoNuevo,
            HttpServletRequest request
    ){
        DescripcionDetalleEmpleado nuevo = descripcionDetalleEmpleadoService.guardarNuevo(toEntity(descripcionDetalleEmpleadoNuevo));
        System.out.println(nuevo);
        return new ModelAndView("redirect:/descripcion-detalle");
    }

    DescripcionDetalleEmpleado toEntity(DescripcionDetalleEmpleadoNuevo act){
        return new DescripcionDetalleEmpleado(
                act.getId(),
                act.getEmpleadoId(),
                act.getDescripcionDetalleId(),
                QuincenaAsignada.valueFrom(act.getQuincenaAsignada()),
                TipoElemento.valueFrom(act.getTipoElemento()),
                act.getMonto(),
                act.getFechaInicio(),
                act.getFechaFinal(),
                true
        );
    }

    @GetMapping("/asignar/{idEmpleado}")
    public ModelAndView asignarDescripcion(
            @PathVariable int idEmpleado,
            HttpServletRequest request
    ){
        Integer sucursal = (Integer) request.getSession().getAttribute("sucursal");
        if (sucursal == null) {
            return new ModelAndView("redirect:/sucursales");
        }
        ModelAndView mav = new ModelAndView("./empleado/empleado-asignar-descripcion-detalle");

        List<ItemComboBox> quincena = Arrays.stream(QuincenaAsignada.values())
                .map(e -> new ItemComboBox(e.getVal(), e.name())).toList();

        List<ItemComboBox> tipos = Arrays.stream(TipoElemento.values())
                .map(e -> new ItemComboBox(e.getVal(), e.name())).toList();

        List<ItemComboBox> descripcionesDetalle = descripcionDetalleService.findAllByObligatorio(false).stream()
                .sorted(Comparator.comparing(DescripcionDetalle::getDescripcion))
                .map(e -> new ItemComboBox(e.getId(), e.getDescripcion())).toList();

        Empleado empleado = empleadoService.getEmpleado(idEmpleado);


        mav.addObject("descripcionesDetalle",descripcionesDetalle);
        mav.addObject("quincena", quincena);
        mav.addObject("tipos", tipos);
        mav.addObject("descipcion", new DescripcionDetalleEmpleadoNuevo(
                0,idEmpleado,empleado.getNombreCompleto().toUpperCase(), MetodosGenerales.cambioFormatoAEstandar(empleado.getSueldoMensual().toString())
        ));
        return mav;
    }

}
