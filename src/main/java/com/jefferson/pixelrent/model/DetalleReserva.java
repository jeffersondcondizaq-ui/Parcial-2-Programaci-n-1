package com.jefferson.pixelrent.model;

public class DetalleReserva {

    private Equipo equipo;
    private int horas;

    public DetalleReserva(Equipo equipo, int horas) {
        this.equipo = equipo;
        this.horas = horas;
    }

    // Getters y Setters
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    // Metodos
    public double calcularSubtotal() {
        throw new UnsupportedOperationException();
    }
}
