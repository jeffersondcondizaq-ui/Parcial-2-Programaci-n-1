package com.jefferson.pixelrent.model;

import java.time.LocalDate;
import java.util.ArrayList;

import com.jefferson.pixelrent.enums.EstadoReserva;
import com.jefferson.pixelrent.enums.Turno;

public class Reserva {

    private String codigo;
    private Cliente cliente;
    private LocalDate fecha;
    private Turno turno;
    private EstadoReserva estado = EstadoReserva.SOLICITADA;
    private Operador operador;
    private ArrayList<DetalleReserva> detalles;

    public Reserva(String codigo, Cliente cliente, LocalDate fecha, Turno turno) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.fecha = fecha;
        this.turno = turno;
        this.detalles = new ArrayList<>();
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Turno getTurno() {
        return turno;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    // Metodos
    public boolean agregarEquipo(Equipo equipo, int horas) {
        throw new UnsupportedOperationException();
    }

    public boolean contieneEquipo(String codigo) {
        throw new UnsupportedOperationException();
    }

    public boolean contieneDron() {
        throw new UnsupportedOperationException();
    }

    public boolean asignarOperador(Operador operador) {
        throw new UnsupportedOperationException();
    }

    public boolean puedeConfirmarse() {
        throw new UnsupportedOperationException();
    }

    public boolean cambiarEstado(EstadoReserva nuevo) {
        throw new UnsupportedOperationException();
    }

    public double calcularValorTotal() {
        throw new UnsupportedOperationException();
    }


}
