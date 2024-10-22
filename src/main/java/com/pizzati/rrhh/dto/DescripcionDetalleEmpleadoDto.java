package com.pizzati.rrhh.dto;

import com.pizzati.rrhh.utilities.QuincenaAsignada;
import com.pizzati.rrhh.utilities.TipoElemento;

import java.math.BigDecimal;

public record DescripcionDetalleEmpleadoDto(
        int id,
        int empleadoId,
        int descripcionDetalleId,
        String descripcionDetalle,
        String quincenaAsignada,
        String tipoElemento,
        BigDecimal monto
) {
}
