package com.jefferson.pixelrent.model;

public class Camara extends Equipo {

    private static final double TARIFA_HORA = 12000;
    private static final double RECARGO_ESTABILIZADOR = 15000;

    private String resolucionMaxima;
    private boolean estabilizador;

    public Camara(String codigo, String marca, String modelo, String resolucionMaxima, boolean estabilizador) {
        super(codigo, marca, modelo);
        this.resolucionMaxima = resolucionMaxima;
        this.estabilizador = estabilizador;
    }

    // Getters y Setters
    public String getResolucionMaxima() {
        return resolucionMaxima;
    }

    public void setResolucionMaxima(String resolucionMaxima) {
        this.resolucionMaxima = resolucionMaxima;
    }

    public boolean getEstabilizador() {
        return estabilizador;
    }

    public void setEstabilizador(boolean estabilizador) {
        this.estabilizador = estabilizador;
    }

    // Metodos
    @Override
    public double calcularCosto(int horas) {
        throw new UnsupportedOperationException();
    }
}
