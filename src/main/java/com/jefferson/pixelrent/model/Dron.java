package com.jefferson.pixelrent.model;

public class Dron extends Equipo {

    private static final double TARIFA_HORA = 18000;
    private static final double RECARGO_BATERIA = 20000;

    private int autonomiaMinutos;
    private boolean bateriaAdicional;

    public Dron(String codigo, String marca, String modelo, int autonomiaMinutos, boolean bateriaAdicional) {
        super(codigo, marca, modelo);
        this.autonomiaMinutos = autonomiaMinutos;
        this.bateriaAdicional = bateriaAdicional;
    }

    // Getters y Setters
    public int getAutonomiaMinutos() {
        return autonomiaMinutos;
    }

    public void setAutonomiaMinutos(int autonomiaMinutos) {
        this.autonomiaMinutos = autonomiaMinutos;
    }

    public boolean getBateriaAdicional() {
        return bateriaAdicional;
    }

    public void setBateriaAdicional(boolean bateriaAdicional) {
        this.bateriaAdicional = bateriaAdicional;
    }

    // Metodos
    @Override
    public double calcularCosto(int horas) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean requiereOperador() {
        throw new UnsupportedOperationException();
    }
}
