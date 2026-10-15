package com.jefferson.pixelrent.model;

public class KitIluminacion extends Equipo {

    private static final double TARIFA_HORA = 10000;
    private static final double RECARGO_CONTROL = 12000;

    private int cantidadLuces;
    private boolean controlInalambrico;

    public KitIluminacion(String codigo, String marca, String modelo, int cantidadLuces, boolean controlInalambrico) {
        super(codigo, marca, modelo);
        this.cantidadLuces = cantidadLuces;
        this.controlInalambrico = controlInalambrico;
    }

    // Getters y Setters
    public int getCantidadLuces() {
        return cantidadLuces;
    }

    public void setCantidadLuces(int cantidadLuces) {
        this.cantidadLuces = cantidadLuces;
    }

    public boolean getControlInalambrico() {
        return controlInalambrico;
    }

    public void setControlInalambrico(boolean controlInalambrico) {
        this.controlInalambrico = controlInalambrico;
    }

    // Metodos
    @Override
    public double calcularCosto(int horas) {
        throw new UnsupportedOperationException();
    }
}
