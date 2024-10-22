package com.pizzati.rrhh.utilities;

public enum TipoMensaje {
    WARNING("warning"),
    SUCCESS("success"),
    INFO("info");
    private final String data;
    TipoMensaje(String data){
        this.data = data;
    }

    public String getData(){
        return data;
    }
}
