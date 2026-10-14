package com.jefferson.pixelrent.model;

import java.time.LocalDate;
import java.util.ArrayList;

import com.jefferson.pixelrent.enums.EstadoReserva;
import com.jefferson.pixelrent.enums.Turno;

public class Empresa {

    private String nit;
    private String nombre;
    private String direccion;
    private ArrayList<Sede> sedes;
    private ArrayList<Cliente> clientes;
    private ArrayList<Operador> operadores;
    private ArrayList<Reserva> reservas;

    public Empresa(String nit, String nombre, String direccion) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.sedes = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.operadores = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public boolean registrarSede(Sede sede) {
        throw new UnsupportedOperationException();
    }

    public Sede buscarSede(String codigo) {
        throw new UnsupportedOperationException();
    }

    public boolean actualizarSede(String codigo, String nombre, String direccion) {
        throw new UnsupportedOperationException();
    }

    public boolean eliminarSede(String codigo) {
        throw new UnsupportedOperationException();
    }

    public boolean registrarEquipo(String codigoSede, Equipo equipo) {
        throw new UnsupportedOperationException();
    }

    public Equipo buscarEquipo(String codigo) {
        throw new UnsupportedOperationException();
    }

    public boolean eliminarEquipo(String codigo) {
        throw new UnsupportedOperationException();
    }

    public boolean registrarCliente(Cliente cliente) {
        throw new UnsupportedOperationException();
    }

    public Cliente buscarCliente(String identificacion) {
        throw new UnsupportedOperationException();
    }

    public boolean registrarOperador(Operador operador) {
        throw new UnsupportedOperationException();
    }

    public Operador buscarOperador(String identificacion) {
        throw new UnsupportedOperationException();
    }

    public boolean actualizarOperador(String id, String certificacion, int anios) {
        throw new UnsupportedOperationException();
    }

    public boolean eliminarOperador(String id) {
        throw new UnsupportedOperationException();
    }

    public boolean registrarReserva(Reserva reserva) {
        throw new UnsupportedOperationException();
    }

    public Reserva buscarReserva(String codigo) {
        throw new UnsupportedOperationException();
    }

    public boolean agregarEquipoAReserva(String codReserva, String codEquipo, int horas) {
        throw new UnsupportedOperationException();
    }

    public boolean asignarOperadorAReserva(String codReserva, String idOperador) {
        throw new UnsupportedOperationException();
    }

    public boolean cambiarEstadoReserva(String codReserva, EstadoReserva nuevo) {
        throw new UnsupportedOperationException();
    }

    public boolean equipoOcupado(Equipo equipo, LocalDate fecha, Turno turno) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<Equipo> consultarEquiposDisponibles(LocalDate fecha, Turno turno) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<Reserva> consultarReservasConfirmadas(String idCliente) {
        throw new UnsupportedOperationException();
    }

    public double consultarValorReserva(String codReserva) {
        throw new UnsupportedOperationException();
    }
}
