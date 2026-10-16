package com.jefferson.pixelrent.model;

public class DetalleReserva {

    private Equipo equipo;
    private int horas;

    public DetalleReserva(Equipo equipo, int horas) {
        this.equipo = equipo;
        this.horas = horas;
    }

    // Getters
    public Equipo getEquipo() {
        return equipo;
    }

    public int getHoras() {
        return horas;
    }

    // Metodos
    public double calcularSubtotal() {
        return equipo.calcularCosto(horas);
    }
}
