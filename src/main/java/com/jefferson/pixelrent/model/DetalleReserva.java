package com.jefferson.pixelrent.model;

public class DetalleReserva {

    private Equipo equipo;
    private int horas;

    public DetalleReserva(Equipo equipo, int horas) {
        this.equipo = equipo;
        this.horas = horas;
    }

    public double calcularSubtotal() {
        throw new UnsupportedOperationException();
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public int getHoras() {
        return horas;
    }
}
