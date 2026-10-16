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

    public Operador getOperador() {
        return operador;
    }

    public ArrayList<DetalleReserva> getDetalles() {
        return detalles;
    }

    // Metodos
    public boolean agregarEquipo(Equipo equipo, int horas) {
        if (equipo == null || horas <= 0 || estado != EstadoReserva.SOLICITADA || contieneEquipo(equipo.getCodigo())) {
            return false;
        }
        return detalles.add(new DetalleReserva(equipo, horas));
    }

    public boolean contieneEquipo(String codigo) {
        for (DetalleReserva detalle : detalles) {
            if (detalle.getEquipo().getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public boolean contieneDron() {
        for (DetalleReserva detalle : detalles) {
            if (detalle.getEquipo() instanceof Dron) {
                return true;
            }
        }
        return false;
    }

    public boolean asignarOperador(Operador operador) {
        if (operador == null || estado != EstadoReserva.SOLICITADA) {
            return false;
        }
        this.operador = operador;
        return true;
    }

    public boolean puedeConfirmarse() {
        if (detalles.isEmpty()) {
            return false;
        }
        if (contieneDron() && operador == null) {
            return false;
        }
        return true;
    }

    public boolean cambiarEstado(EstadoReserva nuevo) {
        if (nuevo == null) {
            return false;
        }
        if (estado == EstadoReserva.CANCELADA || estado == EstadoReserva.FINALIZADA) {
            return false;
        }
        if (nuevo == EstadoReserva.CONFIRMADA && !puedeConfirmarse()) {
            return false;
        }
        this.estado = nuevo;
        return true;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (DetalleReserva detalle : detalles) {
            total += detalle.calcularSubtotal();
        }
        return total;
    }

}
