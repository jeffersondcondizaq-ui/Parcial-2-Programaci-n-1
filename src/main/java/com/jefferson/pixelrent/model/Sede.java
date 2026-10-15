package com.jefferson.pixelrent.model;

import java.util.ArrayList;

public class Sede {

    private String codigo;
    private String nombre;
    private String direccion;
    private ArrayList<Equipo> equipos;

    public Sede(String codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.equipos = new ArrayList<>();
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    // Metodos
    public boolean agregarEquipo(Equipo equipo) {
        throw new UnsupportedOperationException();
    }

    public Equipo buscarEquipo(String codigo) {
        throw new UnsupportedOperationException();
    }

    public boolean eliminarEquipo(String codigo) {
        throw new UnsupportedOperationException();
    }

}
