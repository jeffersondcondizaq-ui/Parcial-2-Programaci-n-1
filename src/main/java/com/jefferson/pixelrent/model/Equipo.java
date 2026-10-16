package com.jefferson.pixelrent.model;

import com.jefferson.pixelrent.enums.Disponibilidad;

public abstract class Equipo {

    protected String codigo;
    protected String marca;
    protected String modelo;
    protected Disponibilidad disponibilidad;

    public Equipo(String codigo, String marca, String modelo) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.disponibilidad = Disponibilidad.DISPONIBLE;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    // Metodos
    public abstract double calcularCosto(int horas);

    public boolean estaDisponible() {
        return disponibilidad == Disponibilidad.DISPONIBLE;
    }

    public boolean requiereOperador() {
        return false;
    }


}
