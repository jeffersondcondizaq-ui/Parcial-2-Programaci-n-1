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

    // Getters y Setters
    public String getNit() {
        return nit;
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

    public ArrayList<Sede> getSedes() {
        return sedes;
    }

    public void setSedes(ArrayList<Sede> sedes) {
        this.sedes = sedes;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Operador> getOperadores() {
        return operadores;
    }

    public void setOperadores(ArrayList<Operador> operadores) {
        this.operadores = operadores;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    // Metodos
    public boolean registrarSede(Sede sede) {
        if (sede == null || buscarSede(sede.getCodigo()) != null) {
            return false;
        }
        return sedes.add(sede);
    }

    public Sede buscarSede(String codigo) {
        for (Sede sede : sedes) {
            if (sede.getCodigo().equals(codigo)) {
                return sede;
            }
        }
        return null;
    }

    public boolean actualizarSede(String codigo, String nombre, String direccion) {
        Sede sede = buscarSede(codigo);
        if (sede == null) {
            return false;
        }
        sede.setNombre(nombre);
        sede.setDireccion(direccion);
        return true;
    }

    public boolean eliminarSede(String codigo) {
        Sede sede = buscarSede(codigo);
        if (sede == null) {
            return false;
        }
        return sedes.remove(sede);
    }

    public boolean registrarEquipo(String codigoSede, Equipo equipo) {
        Sede sede = buscarSede(codigoSede);
        if (sede == null) {
            return false;
        }
        return sede.agregarEquipo(equipo);
    }

    public Equipo buscarEquipo(String codigo) {
        for (Sede sede : sedes) {
            Equipo equipo = sede.buscarEquipo(codigo);
            if (equipo != null) {
                return equipo;
            }
        }
        return null;
    }

    public Sede buscarSedeDeEquipo(String codigo) {
        for (Sede sede : sedes) {
            if (sede.buscarEquipo(codigo) != null) {
                return sede;
            }
        }
        return null;
    }

    public boolean eliminarEquipo(String codigo) {
        Sede sede = buscarSedeDeEquipo(codigo);
        if (sede == null) {
            return false;
        }
        return sede.eliminarEquipo(codigo);
    }

    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null || buscarCliente(cliente.getIdentificacion()) != null) {
            return false;
        }
        return clientes.add(cliente);
    }

    public Cliente buscarCliente(String identificacion) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                return cliente;
            }
        }
        return null;
    }

    public boolean eliminarCliente(String identificacion) {
        Cliente cliente = buscarCliente(identificacion);
        if (cliente == null) {
            return false;
        }
        return clientes.remove(cliente);
    }

    public boolean registrarOperador(Operador operador) {
        if (operador == null || buscarOperador(operador.getIdentificacion()) != null) {
            return false;
        }
        return operadores.add(operador);
    }

    public Operador buscarOperador(String identificacion) {
        for (Operador operador : operadores) {
            if (operador.getIdentificacion().equals(identificacion)) {
                return operador;
            }
        }
        return null;
    }

    public boolean actualizarOperador(String id, String certificacion, int anios) {
        Operador operador = buscarOperador(id);
        if (operador == null) {
            return false;
        }
        operador.setCertificacion(certificacion);
        operador.setAniosExperiencia(anios);
        return true;
    }

    public boolean eliminarOperador(String id) {
        Operador operador = buscarOperador(id);
        if (operador == null) {
            return false;
        }
        return operadores.remove(operador);
    }

    public boolean registrarReserva(Reserva reserva) {
        if (reserva == null || buscarReserva(reserva.getCodigo()) != null) {
            return false;
        }
        return reservas.add(reserva);
    }

    public Reserva buscarReserva(String codigo) {
        for (Reserva reserva : reservas) {
            if (reserva.getCodigo().equals(codigo)) {
                return reserva;
            }
        }
        return null;
    }

    public boolean agregarEquipoAReserva(String codReserva, String codEquipo, int horas) {
        Reserva reserva = buscarReserva(codReserva);
        Equipo equipo = buscarEquipo(codEquipo);
        if (reserva == null || equipo == null) {
            return false;
        }
        if (!equipo.estaDisponible() || equipoOcupado(equipo, reserva.getFecha(), reserva.getTurno())) {
            return false;
        }
        return reserva.agregarEquipo(equipo, horas);
    }

    public boolean asignarOperadorAReserva(String codReserva, String idOperador) {
        Reserva reserva = buscarReserva(codReserva);
        Operador operador = buscarOperador(idOperador);
        if (reserva == null || operador == null) {
            return false;
        }
        return reserva.asignarOperador(operador);
    }

    public boolean cambiarEstadoReserva(String codReserva, EstadoReserva nuevo) {
        Reserva reserva = buscarReserva(codReserva);
        if (reserva == null) {
            return false;
        }
        return reserva.cambiarEstado(nuevo);
    }

    public boolean equipoOcupado(Equipo equipo, LocalDate fecha, Turno turno) {
        for (Reserva reserva : reservas) {
            if (reserva.getEstado() == EstadoReserva.CANCELADA) {
                continue;
            }
            if (reserva.getFecha().equals(fecha) && reserva.getTurno() == turno
                    && reserva.contieneEquipo(equipo.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Equipo> consultarEquiposDisponibles(LocalDate fecha, Turno turno) {
        ArrayList<Equipo> disponibles = new ArrayList<>();
        for (Sede sede : sedes) {
            for (Equipo equipo : sede.getEquipos()) {
                if (equipo.estaDisponible() && !equipoOcupado(equipo, fecha, turno)) {
                    disponibles.add(equipo);
                }
            }
        }
        return disponibles;
    }

    public ArrayList<Reserva> consultarReservasConfirmadas(String idCliente) {
        ArrayList<Reserva> confirmadas = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getEstado() == EstadoReserva.CONFIRMADA
                    && reserva.getCliente().getIdentificacion().equals(idCliente)) {
                confirmadas.add(reserva);
            }
        }
        return confirmadas;
    }

    public double consultarValorReserva(String codReserva) {
        Reserva reserva = buscarReserva(codReserva);
        if (reserva == null) {
            return 0;
        }
        return reserva.calcularValorTotal();
    }
}
