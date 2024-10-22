package com.pizzati.rrhh.controller;

import com.pizzati.rrhh.entity.Sucursal;
import com.pizzati.rrhh.service.SucursalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping()
    public ModelAndView inicio(
    )
    {
        ModelAndView mav = new ModelAndView("./sucursal/sucursal-list");
        List<Sucursal> all =  sucursalService.findAll();
        mav.addObject("sucursales", all);
        return mav;
    }
}
