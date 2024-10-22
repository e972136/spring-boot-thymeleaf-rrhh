package com.pizzati.rrhh.controller;

import com.pizzati.rrhh.entity.Sucursal;
import com.pizzati.rrhh.service.SucursalService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sistema/rrhh/menu")
public class MenuPrincipalController {


    private final SucursalService sucursalService;

    public MenuPrincipalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }


    @GetMapping("/{sucursal}")
    public ModelAndView opciones(
            @PathVariable int sucursal,
            HttpServletRequest request
    ){
        Sucursal sucursalEntity =  sucursalService.findById(sucursal);
        ModelAndView mav = new ModelAndView("./menu-principal");
        request.getSession().setAttribute("sucursal", sucursal);
        request.getSession().setAttribute("ciaNombre", sucursalEntity.getNombreSucursal());
        return mav;
    }


}
