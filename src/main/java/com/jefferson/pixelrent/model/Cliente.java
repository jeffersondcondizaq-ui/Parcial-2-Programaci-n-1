package com.jefferson.pixelrent.model;

public class Cliente extends Persona {

    private String telefono;
    private String correo;

    public Cliente(String identificacion, String nombreCompleto, String telefono, String correo) {
        super(identificacion, nombreCompleto);
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }
}
