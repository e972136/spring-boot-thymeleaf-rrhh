package com.pizzati.rrhh.utilities;

public enum QuincenaAsignada {
    QUINCENA_UNO(1,1),
    QUINCENA_DOS(2,1),
    QUINCENAL(3,2),
    UNICA(4,1),
    PENDIENTE(0,0);

    private final int val;
    private final int mensual;

    QuincenaAsignada(int val,int mensual) {

        this.val = val;
        this.mensual = mensual;
    }

    public int getVal() {
        return val;
    }

    public int getMensual() {
        return mensual;
    }

    public static QuincenaAsignada valueFrom(int v){
        for(QuincenaAsignada act: QuincenaAsignada.values()){
            if(v == act.getVal()){
                return  act;
            }
        }
        return QuincenaAsignada.PENDIENTE;
    }
}
