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
        if (equipo == null || buscarEquipo(equipo.getCodigo()) != null) {
            return false;
        }
        return equipos.add(equipo);
    }

    public Equipo buscarEquipo(String codigo) {
        for (Equipo equipo : equipos) {
            if (equipo.getCodigo().equals(codigo)) {
                return equipo;
            }
        }
        return null;
    }

    public boolean eliminarEquipo(String codigo) {
        Equipo equipo = buscarEquipo(codigo);
        if (equipo == null) {
            return false;
        }
        return equipos.remove(equipo);
    }

}
