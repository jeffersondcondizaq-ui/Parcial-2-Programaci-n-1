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

    @Override
    public double calcularCosto(int horas) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean requiereOperador() {
        throw new UnsupportedOperationException();
    }
}
