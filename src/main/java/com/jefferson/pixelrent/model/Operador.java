package com.jefferson.pixelrent.model;

public class Operador extends Persona {

    private String certificacion;
    private int aniosExperiencia;

    public Operador(String identificacion, String nombreCompleto, String certificacion, int aniosExperiencia) {
        super(identificacion, nombreCompleto);
        this.certificacion = certificacion;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
}
