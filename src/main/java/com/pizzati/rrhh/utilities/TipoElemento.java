package com.pizzati.rrhh.utilities;

public enum TipoElemento {
    INGRESO(1),
    DEDUCCION(-1);

    private final int val;

    TipoElemento(int val) {
        this.val = val;
    }

    public int getVal(){
        return val;
    }

    public static TipoElemento valueFrom(int v){
        for(TipoElemento act: TipoElemento.values()){
            if(v == act.getVal()){
                return  act;
            }
        }
        return TipoElemento.INGRESO;
    }
}
