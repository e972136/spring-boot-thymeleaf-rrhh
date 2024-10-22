package com.pizzati.rrhh.controller;

import com.pizzati.rrhh.dto.DescripcionDetalleEmpleadoDto;
import com.pizzati.rrhh.dto.EmpleadoDto;
import com.pizzati.rrhh.dto.EmpleadoListado;
import com.pizzati.rrhh.entity.Departamento;
import com.pizzati.rrhh.entity.DescripcionDetalle;
import com.pizzati.rrhh.entity.DescripcionDetalleEmpleado;
import com.pizzati.rrhh.entity.Empleado;
import com.pizzati.rrhh.service.*;
import com.pizzati.rrhh.utilities.ItemComboBox;
import com.pizzati.rrhh.utilities.TipoElemento;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    private final EmpleadoService empleadoService;
    private final DepartamentoService departamentoService;

    private final DescripcionDetalleService descripcionDetalleService;
    private final PuestoService puestoService;

    private final DescripcionDetalleEmpleadoService descripcionDetalleEmpleadoService;

    public EmpleadoController(EmpleadoService empleadoService, DepartamentoService departamentoService, DescripcionDetalleService descripcionDetalleService, PuestoService puestoService, DescripcionDetalleEmpleadoService descripcionDetalleEmpleadoService) {
        this.empleadoService = empleadoService;
        this.departamentoService = departamentoService;
        this.descripcionDetalleService = descripcionDetalleService;
        this.puestoService = puestoService;
        this.descripcionDetalleEmpleadoService = descripcionDetalleEmpleadoService;
    }

    @GetMapping()
    public ModelAndView listadoEmpleados(
            @RequestParam(required = false) String busqueda,
            @RequestParam(required = false) String departamento,
            @PageableDefault(size = 20,sort = "nombreCompleto",direction = Sort.Direction.ASC) Pageable page,
            HttpServletRequest request
    ){
        Integer sucursal = (Integer) request.getSession().getAttribute("sucursal");
        if (sucursal == null) {
            return new ModelAndView("redirect:/sucursales");
        }

        ModelAndView mav = new ModelAndView("./empleado/empleado-list");
        List<Departamento> allDepartamentos = departamentoService.findAll();

        Map<Integer, String> departamentos = allDepartamentos.stream().collect(Collectors.toMap(Departamento::getId, Departamento::getNombreDepartamento));

        Page<EmpleadoListado> empleados = empleadoService.allEmpleadosSucursal(sucursal,page).map(
                e->{
                    return new EmpleadoListado(e.getId(),e.getIdentidad(),e.getNombreCompleto(),departamentos.getOrDefault(e.getDepartamentoId(),"xxx"));
                }
        );
        mav.addObject("empleados",empleados);
        mav.addObject("departamentos",allDepartamentos);

        return mav;
    }

    @GetMapping("/editar/{idEmpleado}")
    public ModelAndView editarEmpleado(
            @PathVariable int idEmpleado,
            HttpServletRequest request
    ){
        Integer sucursal = (Integer) request.getSession().getAttribute("sucursal");
        if (sucursal == null) {
            return new ModelAndView("redirect:/sucursales");
        }

        Map<Integer, String> descripcinesDisponibles = descripcionDetalleService.findAllSet()
                .stream().collect(Collectors.toMap(DescripcionDetalle::getId, DescripcionDetalle::getDescripcion));
/*
Map<Integer, String> departamentos = allDepartamentos.stream().collect(Collectors.toMap(Departamento::getId, Departamento::getNombreDepartamento));
 */
        List<DescripcionDetalleEmpleado> detalles =  descripcionDetalleEmpleadoService.findAllByEmpleadoId(idEmpleado)
                .stream()
                .filter(DescripcionDetalleEmpleado::isActivo).toList();

        Set<DescripcionDetalleEmpleadoDto> detallesIngreso = detalles.stream()
                .filter(e->e.getTipoElemento().equals(TipoElemento.INGRESO))
                .map(i->{
                    return new DescripcionDetalleEmpleadoDto(
                         i.getId(),
                         i.getEmpleadoId(),
                         i.getDescripcionDetalleId(),
                            descripcinesDisponibles.getOrDefault(i.getDescripcionDetalleId(),"xxx"),
                         i.getQuincenaAsignada().name(),
                         i.getTipoElemento().name(),
                         i.getMonto()
                    );
                })
                .collect(Collectors.toSet());

        Set<DescripcionDetalleEmpleadoDto> detallesDeduccion = detalles.stream()
                .filter(e->e.getTipoElemento().equals(TipoElemento.DEDUCCION))
                .map(i-> {
                    return new DescripcionDetalleEmpleadoDto(
                            i.getId(),
                            i.getEmpleadoId(),
                            i.getDescripcionDetalleId(),
                            descripcinesDisponibles.getOrDefault(i.getDescripcionDetalleId(), "xxx"),
                            i.getQuincenaAsignada().name(),
                            i.getTipoElemento().name(),
                            i.getMonto()
                    );
                })
                .collect(Collectors.toSet());


        Empleado empleadoBd = empleadoService.getEmpleado(idEmpleado);

        ModelAndView mav = new ModelAndView("./empleado/empleado-editar");

        BigDecimal reduceDEDUCCION = detalles.stream()
                .filter(f->f.getTipoElemento().equals(TipoElemento.DEDUCCION))
                .map(e -> e.getMonto().multiply(BigDecimal.valueOf(e.getQuincenaAsignada().getMensual())))
                .reduce(BigDecimal.ZERO, (subtotal, element) -> element.add(subtotal));

        BigDecimal reduceINGRESO = detalles.stream()
                .filter(f->f.getTipoElemento().equals(TipoElemento.INGRESO))
                .map(e -> e.getMonto().multiply(BigDecimal.valueOf(e.getQuincenaAsignada().getMensual())))
                .reduce(BigDecimal.ZERO, (subtotal, element) -> element.add(subtotal));

        EmpleadoDto empleadoDto = EmpleadoDto.of(
                empleadoBd.getId(),
                empleadoBd.getIdentidad(),
                empleadoBd.getNombreCompleto(),
                empleadoBd.getSucursalId(),
                empleadoBd.getDepartamentoId(),
                empleadoBd.getPuestoId(),
                empleadoBd.getSueldoMensual(),
                empleadoBd.getFechaContratacion(),
                empleadoBd.getFechaUltimoPago(),
                empleadoBd.getFechaDespido(),
                empleadoBd.getFechaUltimasVacaciones(),
                empleadoBd.getObs(),
                reduceDEDUCCION,
                reduceINGRESO,
                empleadoBd.getSueldoMensual().add(reduceINGRESO).subtract(reduceDEDUCCION)
        );

        List<ItemComboBox> departamentos = departamentoService.findAll().stream()
                .map(e -> new ItemComboBox(e.getId(), e.getNombreDepartamento()))
                .toList();

        List<ItemComboBox> puestos = puestoService.findAll().stream()
                .map(e -> new ItemComboBox(e.getId(), e.getNombrePuesto()))
                .toList();


        mav.addObject("empleado",empleadoDto);
        mav.addObject("detallesIngreso",detallesIngreso);
        mav.addObject("detallesDeduccion",detallesDeduccion);
        mav.addObject("departamentos",departamentos);
        mav.addObject("puestos",puestos);
        return mav;
    }
}
