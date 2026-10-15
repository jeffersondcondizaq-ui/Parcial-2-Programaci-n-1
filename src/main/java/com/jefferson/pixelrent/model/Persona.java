package com.jefferson.pixelrent.model;

public abstract class Persona {

    protected String identificacion;
    protected String nombreCompleto;

    public Persona(String identificacion, String nombreCompleto) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
    }

    // Getters y Setters
    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }


}
