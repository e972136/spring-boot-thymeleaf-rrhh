package com.pizzati.rrhh.dto;

import com.pizzati.rrhh.entity.DescripcionDetalle;
import com.pizzati.rrhh.utilities.QuincenaAsignada;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescripcionDetalleNuevo
{
        Integer id;
        String descripcion;
        BigDecimal monto;
        boolean obligatoria;
        int quincenaAsignada;
        boolean activo;

    public DescripcionDetalleNuevo(Integer id, BigDecimal monto, boolean obligatoria, boolean activo) {
        this.id = id;
        this.monto = monto;
        this.obligatoria = obligatoria;
        this.activo = activo;
    }

    public DescripcionDetalle fromEntity(){
        QuincenaAsignada tmp = QuincenaAsignada.PENDIENTE;
        for(QuincenaAsignada x: QuincenaAsignada.values()){
            if(x.getVal()==quincenaAsignada){
                tmp = x;
            }
        }
        return new DescripcionDetalle(
                id,descripcion,monto,obligatoria,tmp,activo
        );
    }
}
