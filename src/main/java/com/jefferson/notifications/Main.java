package com.jefferson.notifications;

import com.jefferson.pixelrent.enums.Disponibilidad;
import com.jefferson.pixelrent.enums.EstadoReserva;
import com.jefferson.pixelrent.enums.Turno;
import com.jefferson.pixelrent.model.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Main {
    static void main() {

        Empresa pixelRent = new Empresa("900123456-7", "PixelRent", "Calle 10 # 20-30");

        int option;

        do {
            option = leerOpcion("~~ PIXELRENT 📷~~\n" +
                    "¿Que desea realizar hoy?\n" +
                    "\n 1. Gestionar Sede" +
                    "\n 2. Gestionar Equipo" +
                    "\n 3. Gestionar Cliente" +
                    "\n 4. Gestionar Operador" +
                    "\n 5. Gestionar Reserva" +
                    "\n 0. Salir del sistema");

            switch (option) {
                case 1:
                    gestionarSede(pixelRent);
                    break;
                case 2:
                    gestionarEquipo(pixelRent);
                    break;
                case 3:
                    gestionarCliente(pixelRent);
                    break;
                case 4:
                    gestionarOperador(pixelRent);
                    break;
                case 5:
                    gestionarReserva(pixelRent);
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (option != 0);
    }

    // ---------- Helpers de entrada/salida ----------

    private static int leerOpcion(String mensaje) {
        String input = JOptionPane.showInputDialog(mensaje);
        if (input == null) {
            return 0;
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String pedirTexto(String etiqueta) {
        return JOptionPane.showInputDialog(etiqueta);
    }

    private static Integer pedirEntero(String etiqueta) {
        String input = JOptionPane.showInputDialog(etiqueta);
        if (input == null) {
            return null;
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            info("Debe ingresar un número entero válido.");
            return null;
        }
    }

    private static boolean pedirBooleano(String etiqueta) {
        int resultado = JOptionPane.showConfirmDialog(null, etiqueta, "Confirmar", JOptionPane.YES_NO_OPTION);
        return resultado == JOptionPane.YES_OPTION;
    }

    private static LocalDate pedirFecha(String etiqueta) {
        String input = JOptionPane.showInputDialog(etiqueta + " (formato: aaaa-mm-dd)");
        if (input == null) {
            return null;
        }
        try {
            return LocalDate.parse(input.trim());
        } catch (DateTimeParseException e) {
            info("Fecha inválida. Use el formato aaaa-mm-dd.");
            return null;
        }
    }

    private static Turno pedirTurno() {
        Integer opcion = pedirEntero("Seleccione el turno:\n 1. Mañana\n 2. Tarde\n 3. Noche");
        if (opcion == null) {
            return null;
        }
        return switch (opcion) {
            case 1 -> Turno.MANANA;
            case 2 -> Turno.TARDE;
            case 3 -> Turno.NOCHE;
            default -> null;
        };
    }

    private static void info(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    // ---------- Sede ----------

    private static void gestionarSede(Empresa empresa) {
        int sedeOption;
        do {
            sedeOption = leerOpcion("~~ GESTIONAR SEDE ~~\n" +
                    "Seleccione una opcion\n" +
                    "\n 1. Registrar Sede" +
                    "\n 2. Mostrar Sedes" +
                    "\n 3. Mostrar Sede por Codigo" +
                    "\n 4. Actualizar Sede " +
                    "\n 5. Eliminar Sede" +
                    "\n 0. Regresar");
            switch (sedeOption) {
                case 1:
                    registrarSede(empresa);
                    break;
                case 2:
                    mostrarSedes(empresa);
                    break;
                case 3:
                    mostrarSedePorCodigo(empresa);
                    break;
                case 4:
                    actualizarSede(empresa);
                    break;
                case 5:
                    eliminarSede(empresa);
                    break;
                case 0:
                    break;
                default:
                    info("Opción no válida.");
            }
        } while (sedeOption != 0);
    }

    private static void registrarSede(Empresa empresa) {
        String codigo = pedirTexto("Código de la sede:");
        if (codigo == null || codigo.isBlank()) {
            info("Registro cancelado.");
            return;
        }
        String nombre = pedirTexto("Nombre de la sede:");
        String direccion = pedirTexto("Dirección de la sede:");
        if (nombre == null || direccion == null) {
            info("Registro cancelado.");
            return;
        }
        boolean ok = empresa.registrarSede(new Sede(codigo.trim(), nombre, direccion));
        info(ok ? "Sede registrada con éxito." : "Ya existe una sede con ese código.");
    }

    private static void mostrarSedes(Empresa empresa) {
        if (empresa.getSedes().isEmpty()) {
            info("No hay sedes registradas.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Sede sede : empresa.getSedes()) {
            sb.append(sede.getCodigo()).append(" - ").append(sede.getNombre())
                    .append(" (").append(sede.getDireccion()).append(") - ")
                    .append(sede.getEquipos().size()).append(" equipo(s)\n");
        }
        info(sb.toString());
    }

    private static void mostrarSedePorCodigo(Empresa empresa) {
        String codigo = pedirTexto("Código de la sede:");
        if (codigo == null) {
            return;
        }
        Sede sede = empresa.buscarSede(codigo.trim());
        if (sede == null) {
            info("No se encontró la sede.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Código: ").append(sede.getCodigo()).append("\n");
        sb.append("Nombre: ").append(sede.getNombre()).append("\n");
        sb.append("Dirección: ").append(sede.getDireccion()).append("\n");
        sb.append("Equipos:\n");
        if (sede.getEquipos().isEmpty()) {
            sb.append(" (sin equipos)\n");
        }
        for (Equipo equipo : sede.getEquipos()) {
            sb.append(" - ").append(equipo.getCodigo()).append(" ").append(equipo.getMarca())
                    .append(" ").append(equipo.getModelo()).append(" [").append(equipo.getDisponibilidad()).append("]\n");
        }
        info(sb.toString());
    }

    private static void actualizarSede(Empresa empresa) {
        String codigo = pedirTexto("Código de la sede a actualizar:");
        if (codigo == null) {
            return;
        }
        if (empresa.buscarSede(codigo.trim()) == null) {
            info("No se encontró la sede.");
            return;
        }
        String nombre = pedirTexto("Nuevo nombre:");
        String direccion = pedirTexto("Nueva dirección:");
        if (nombre == null || direccion == null) {
            info("Actualización cancelada.");
            return;
        }
        boolean ok = empresa.actualizarSede(codigo.trim(), nombre, direccion);
        info(ok ? "Sede actualizada con éxito." : "No se pudo actualizar la sede.");
    }

    private static void eliminarSede(Empresa empresa) {
        String codigo = pedirTexto("Código de la sede a eliminar:");
        if (codigo == null) {
            return;
        }
        if (!pedirBooleano("¿Seguro que desea eliminar la sede " + codigo + "?")) {
            return;
        }
        boolean ok = empresa.eliminarSede(codigo.trim());
        info(ok ? "Sede eliminada con éxito." : "No se encontró la sede.");
    }

    // ---------- Equipo ----------

    private static void gestionarEquipo(Empresa empresa) {
        int equOption;
        do {
            equOption = leerOpcion("~~ GESTIONAR EQUIPO ~~\n" +
                    "¿Que desea realizar hoy?\n" +
                    "\n 1. Gestionar Camaras" +
                    "\n 2. Gestionar Drones" +
                    "\n 3. Gestionar Kits Iluminacion" +
                    "\n 4. Cambiar Disponibilidad de Equipo" +
                    "\n 5. Consultar Equipos Disponibles por Fecha y Turno" +
                    "\n 0. Regresar");
            switch (equOption) {
                case 1:
                    gestionarCamara(empresa);
                    break;
                case 2:
                    gestionarDron(empresa);
                    break;
                case 3:
                    gestionarKitIluminacion(empresa);
                    break;
                case 4:
                    cambiarDisponibilidadEquipo(empresa);
                    break;
                case 5:
                    consultarEquiposDisponibles(empresa);
                    break;
                case 0:
                    break;
                default:
                    info("Opción no válida.");
            }
        } while (equOption != 0);
    }

    private static void consultarEquiposDisponibles(Empresa empresa) {
        LocalDate fecha = pedirFecha("Fecha a consultar");
        if (fecha == null) {
            return;
        }
        Turno turno = pedirTurno();
        if (turno == null) {
            return;
        }
        ArrayList<Equipo> disponibles = empresa.consultarEquiposDisponibles(fecha, turno);
        if (disponibles.isEmpty()) {
            info("No hay equipos disponibles para esa fecha y turno.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Equipo equipo : disponibles) {
            sb.append(equipo.getCodigo()).append(" - ").append(equipo.getMarca()).append(" ")
                    .append(equipo.getModelo()).append(" [").append(equipo.getClass().getSimpleName()).append("]\n");
        }
        info(sb.toString());
    }

    private static void cambiarDisponibilidadEquipo(Empresa empresa) {
        String codigo = pedirTexto("Código del equipo:");
        if (codigo == null) {
            return;
        }
        Equipo equipo = empresa.buscarEquipo(codigo.trim());
        if (equipo == null) {
            info("No se encontró el equipo.");
            return;
        }
        Integer opcion = pedirEntero("Disponibilidad actual: " + equipo.getDisponibilidad() +
                "\nSeleccione la nueva disponibilidad:\n 1. Disponible\n 2. En mantenimiento\n 3. Fuera de servicio");
        if (opcion == null) {
            return;
        }
        Disponibilidad nueva = switch (opcion) {
            case 1 -> Disponibilidad.DISPONIBLE;
            case 2 -> Disponibilidad.EN_MANTENIMIENTO;
            case 3 -> Disponibilidad.FUERA_DE_SERVICIO;
            default -> null;
        };
        if (nueva == null) {
            info("Opción no válida.");
            return;
        }
        equipo.setDisponibilidad(nueva);
        info("Disponibilidad actualizada a " + nueva + ".");
    }

    private static void mostrarEquipos(Empresa empresa, Class<? extends Equipo> tipo, String etiqueta) {
        StringBuilder sb = new StringBuilder();
        for (Sede sede : empresa.getSedes()) {
            for (Equipo equipo : sede.getEquipos()) {
                if (tipo.isInstance(equipo)) {
                    sb.append(equipo.getCodigo()).append(" - ").append(equipo.getMarca()).append(" ")
                            .append(equipo.getModelo()).append(" (Sede: ").append(sede.getCodigo())
                            .append(") [").append(equipo.getDisponibilidad()).append("]\n");
                }
            }
        }
        info(sb.isEmpty() ? ("No hay " + etiqueta.toLowerCase() + " registrados.") : sb.toString());
    }

    private static void eliminarEquipo(Empresa empresa, String etiqueta) {
        String codigo = pedirTexto("Código del " + etiqueta + " a eliminar:");
        if (codigo == null) {
            return;
        }
        if (!pedirBooleano("¿Seguro que desea eliminar el equipo " + codigo + "?")) {
            return;
        }
        boolean ok = empresa.eliminarEquipo(codigo.trim());
        info(ok ? "Equipo eliminado con éxito." : "No se encontró el equipo.");
    }

    // ---------- Camara ----------

    private static void gestionarCamara(Empresa empresa) {
        int camOption;
        do {
            camOption = leerOpcion("~~ GESTIONAR CAMARA ~~\n" +
                    "Seleccione una opcion\n" +
                    "\n 1. Registrar Camara" +
                    "\n 2. Mostrar Camaras" +
                    "\n 3. Mostrar Camara por Codigo" +
                    "\n 4. Actualizar Camara " +
                    "\n 5. Eliminar Camara" +
                    "\n 0. Regresar");
            switch (camOption) {
                case 1:
                    registrarCamara(empresa);
                    break;
                case 2:
                    mostrarEquipos(empresa, Camara.class, "cámaras");
                    break;
                case 3:
                    mostrarCamaraPorCodigo(empresa);
                    break;
                case 4:
                    actualizarCamara(empresa);
                    break;
                case 5:
                    eliminarEquipo(empresa, "cámara");
                    break;
                case 0:
                    break;
                default:
                    info("Opción no válida.");
            }
        } while (camOption != 0);
    }

    private static void registrarCamara(Empresa empresa) {
        String codigoSede = pedirTexto("Código de la sede donde se registrará la cámara:");
        if (codigoSede == null) {
            return;
        }
        if (empresa.buscarSede(codigoSede.trim()) == null) {
            info("No existe esa sede.");
            return;
        }
        String codigo = pedirTexto("Código de la cámara:");
        String marca = pedirTexto("Marca:");
        String modelo = pedirTexto("Modelo:");
        String resolucion = pedirTexto("Resolución máxima (ej. 4K):");
        if (codigo == null || marca == null || modelo == null || resolucion == null) {
            info("Registro cancelado.");
            return;
        }
        boolean estabilizador = pedirBooleano("¿Tiene estabilizador?");
        boolean ok = empresa.registrarEquipo(codigoSede.trim(), new Camara(codigo.trim(), marca, modelo, resolucion, estabilizador));
        info(ok ? "Cámara registrada con éxito." : "No se pudo registrar (código duplicado o sede inválida).");
    }

    private static void mostrarCamaraPorCodigo(Empresa empresa) {
        String codigo = pedirTexto("Código de la cámara:");
        if (codigo == null) {
            return;
        }
        Equipo equipo = empresa.buscarEquipo(codigo.trim());
        if (!(equipo instanceof Camara camara)) {
            info("No se encontró una cámara con ese código.");
            return;
        }
        info("Código: " + camara.getCodigo() + "\nMarca: " + camara.getMarca() + "\nModelo: " + camara.getModelo()
                + "\nResolución máxima: " + camara.getResolucionMaxima() + "\nEstabilizador: " + camara.getEstabilizador()
                + "\nDisponibilidad: " + camara.getDisponibilidad());
    }

    private static void actualizarCamara(Empresa empresa) {
        String codigo = pedirTexto("Código de la cámara a actualizar:");
        if (codigo == null) {
            return;
        }
        Equipo equipo = empresa.buscarEquipo(codigo.trim());
        if (!(equipo instanceof Camara camara)) {
            info("No se encontró una cámara con ese código.");
            return;
        }
        String marca = pedirTexto("Nueva marca:");
        String modelo = pedirTexto("Nuevo modelo:");
        String resolucion = pedirTexto("Nueva resolución máxima:");
        if (marca == null || modelo == null || resolucion == null) {
            info("Actualización cancelada.");
            return;
        }
        boolean estabilizador = pedirBooleano("¿Tiene estabilizador?");
        camara.setMarca(marca);
        camara.setModelo(modelo);
        camara.setResolucionMaxima(resolucion);
        camara.setEstabilizador(estabilizador);
        info("Cámara actualizada con éxito.");
    }

    // ---------- Dron ----------

    private static void gestionarDron(Empresa empresa) {
        int dronOption;
        do {
            dronOption = leerOpcion("~~ GESTIONAR DRON ~~\n" +
                    "Seleccione una opcion\n" +
                    "\n 1. Registrar Dron" +
                    "\n 2. Mostrar Drones" +
                    "\n 3. Mostrar Dron por Codigo" +
                    "\n 4. Actualizar Dron " +
                    "\n 5. Eliminar Dron" +
                    "\n 0. Regresar");
            switch (dronOption) {
                case 1:
                    registrarDron(empresa);
                    break;
                case 2:
                    mostrarEquipos(empresa, Dron.class, "drones");
                    break;
                case 3:
                    mostrarDronPorCodigo(empresa);
                    break;
                case 4:
                    actualizarDron(empresa);
                    break;
                case 5:
                    eliminarEquipo(empresa, "dron");
                    break;
                case 0:
                    break;
                default:
                    info("Opción no válida.");
            }
        } while (dronOption != 0);
    }

    private static void registrarDron(Empresa empresa) {
        String codigoSede = pedirTexto("Código de la sede donde se registrará el dron:");
        if (codigoSede == null) {
            return;
        }
        if (empresa.buscarSede(codigoSede.trim()) == null) {
            info("No existe esa sede.");
            return;
        }
        String codigo = pedirTexto("Código del dron:");
        String marca = pedirTexto("Marca:");
        String modelo = pedirTexto("Modelo:");
        Integer autonomia = pedirEntero("Autonomía en minutos:");
        if (codigo == null || marca == null || modelo == null || autonomia == null) {
            info("Registro cancelado.");
            return;
        }
        boolean bateriaAdicional = pedirBooleano("¿Incluye batería adicional?");
        boolean ok = empresa.registrarEquipo(codigoSede.trim(), new Dron(codigo.trim(), marca, modelo, autonomia, bateriaAdicional));
        info(ok ? "Dron registrado con éxito." : "No se pudo registrar (código duplicado o sede inválida).");
    }

    private static void mostrarDronPorCodigo(Empresa empresa) {
        String codigo = pedirTexto("Código del dron:");
        if (codigo == null) {
            return;
        }
        Equipo equipo = empresa.buscarEquipo(codigo.trim());
        if (!(equipo instanceof Dron dron)) {
            info("No se encontró un dron con ese código.");
            return;
        }
        info("Código: " + dron.getCodigo() + "\nMarca: " + dron.getMarca() + "\nModelo: " + dron.getModelo()
                + "\nAutonomía (min): " + dron.getAutonomiaMinutos() + "\nBatería adicional: " + dron.getBateriaAdicional()
                + "\nDisponibilidad: " + dron.getDisponibilidad());
    }

    private static void actualizarDron(Empresa empresa) {
        String codigo = pedirTexto("Código del dron a actualizar:");
        if (codigo == null) {
            return;
        }
        Equipo equipo = empresa.buscarEquipo(codigo.trim());
        if (!(equipo instanceof Dron dron)) {
            info("No se encontró un dron con ese código.");
            return;
        }
        String marca = pedirTexto("Nueva marca:");
        String modelo = pedirTexto("Nuevo modelo:");
        Integer autonomia = pedirEntero("Nueva autonomía en minutos:");
        if (marca == null || modelo == null || autonomia == null) {
            info("Actualización cancelada.");
            return;
        }
        boolean bateriaAdicional = pedirBooleano("¿Incluye batería adicional?");
        dron.setMarca(marca);
        dron.setModelo(modelo);
        dron.setAutonomiaMinutos(autonomia);
        dron.setBateriaAdicional(bateriaAdicional);
        info("Dron actualizado con éxito.");
    }

    // ---------- Kit Iluminacion ----------

    private static void gestionarKitIluminacion(Empresa empresa) {
        int ilumOption;
        do {
            ilumOption = leerOpcion("~~ GESTIONAR KIT ILUMINACION ~~\n" +
                    "Seleccione una opcion\n" +
                    "\n 1. Registrar Kit Ilumnación" +
                    "\n 2. Mostrar Kits Ilumnación" +
                    "\n 3. Mostrar Kit Ilumnación por Codigo" +
                    "\n 4. Actualizar Kit Ilumnación " +
                    "\n 5. Eliminar Kit Ilumnación" +
                    "\n 0. Regresar");
            switch (ilumOption) {
                case 1:
                    registrarKitIluminacion(empresa);
                    break;
                case 2:
                    mostrarEquipos(empresa, KitIluminacion.class, "kits de iluminación");
                    break;
                case 3:
                    mostrarKitIluminacionPorCodigo(empresa);
                    break;
                case 4:
                    actualizarKitIluminacion(empresa);
                    break;
                case 5:
                    eliminarEquipo(empresa, "kit de iluminación");
                    break;
                case 0:
                    break;
                default:
                    info("Opción no válida.");
            }
        } while (ilumOption != 0);
    }

    private static void registrarKitIluminacion(Empresa empresa) {
        String codigoSede = pedirTexto("Código de la sede donde se registrará el kit:");
        if (codigoSede == null) {
            return;
        }
        if (empresa.buscarSede(codigoSede.trim()) == null) {
            info("No existe esa sede.");
            return;
        }
        String codigo = pedirTexto("Código del kit:");
        String marca = pedirTexto("Marca:");
        String modelo = pedirTexto("Modelo:");
        Integer cantidadLuces = pedirEntero("Cantidad de luces:");
        if (codigo == null || marca == null || modelo == null || cantidadLuces == null) {
            info("Registro cancelado.");
            return;
        }
        boolean controlInalambrico = pedirBooleano("¿Tiene control inalámbrico?");
        boolean ok = empresa.registrarEquipo(codigoSede.trim(), new KitIluminacion(codigo.trim(), marca, modelo, cantidadLuces, controlInalambrico));
        info(ok ? "Kit de iluminación registrado con éxito." : "No se pudo registrar (código duplicado o sede inválida).");
    }

    private static void mostrarKitIluminacionPorCodigo(Empresa empresa) {
        String codigo = pedirTexto("Código del kit:");
        if (codigo == null) {
            return;
        }
        Equipo equipo = empresa.buscarEquipo(codigo.trim());
        if (!(equipo instanceof KitIluminacion kit)) {
            info("No se encontró un kit de iluminación con ese código.");
            return;
        }
        info("Código: " + kit.getCodigo() + "\nMarca: " + kit.getMarca() + "\nModelo: " + kit.getModelo()
                + "\nCantidad de luces: " + kit.getCantidadLuces() + "\nControl inalámbrico: " + kit.getControlInalambrico()
                + "\nDisponibilidad: " + kit.getDisponibilidad());
    }

    private static void actualizarKitIluminacion(Empresa empresa) {
        String codigo = pedirTexto("Código del kit a actualizar:");
        if (codigo == null) {
            return;
        }
        Equipo equipo = empresa.buscarEquipo(codigo.trim());
        if (!(equipo instanceof KitIluminacion kit)) {
            info("No se encontró un kit de iluminación con ese código.");
            return;
        }
        String marca = pedirTexto("Nueva marca:");
        String modelo = pedirTexto("Nuevo modelo:");
        Integer cantidadLuces = pedirEntero("Nueva cantidad de luces:");
        if (marca == null || modelo == null || cantidadLuces == null) {
            info("Actualización cancelada.");
            return;
        }
        boolean controlInalambrico = pedirBooleano("¿Tiene control inalámbrico?");
        kit.setMarca(marca);
        kit.setModelo(modelo);
        kit.setCantidadLuces(cantidadLuces);
        kit.setControlInalambrico(controlInalambrico);
        info("Kit de iluminación actualizado con éxito.");
    }

    // ---------- Cliente ----------

    private static void gestionarCliente(Empresa empresa) {
        int clieOption;
        do {
            clieOption = leerOpcion("~~ GESTIONAR CLIENTE ~~\n" +
                    "Seleccione una opcion\n" +
                    "\n 1. Registrar Cliente" +
                    "\n 2. Mostrar Cliente" +
                    "\n 3. Mostrar Cliente por ID" +
                    "\n 4. Eliminar Cliente" +
                    "\n 0. Regresar");
            switch (clieOption) {
                case 1:
                    registrarCliente(empresa);
                    break;
                case 2:
                    mostrarClientes(empresa);
                    break;
                case 3:
                    mostrarClientePorId(empresa);
                    break;
                case 4:
                    eliminarCliente(empresa);
                    break;
                case 0:
                    break;
                default:
                    info("Opción no válida.");
            }
        } while (clieOption != 0);
    }

    private static void registrarCliente(Empresa empresa) {
        String identificacion = pedirTexto("Identificación del cliente:");
        if (identificacion == null || identificacion.isBlank()) {
            info("Registro cancelado.");
            return;
        }
        String nombre = pedirTexto("Nombre completo:");
        String telefono = pedirTexto("Teléfono:");
        String correo = pedirTexto("Correo:");
        if (nombre == null || telefono == null || correo == null) {
            info("Registro cancelado.");
            return;
        }
        boolean ok = empresa.registrarCliente(new Cliente(identificacion.trim(), nombre, telefono, correo));
        info(ok ? "Cliente registrado con éxito." : "Ya existe un cliente con esa identificación.");
    }

    private static void mostrarClientes(Empresa empresa) {
        if (empresa.getClientes().isEmpty()) {
            info("No hay clientes registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : empresa.getClientes()) {
            sb.append(cliente.getIdentificacion()).append(" - ").append(cliente.getNombreCompleto())
                    .append(" | Tel: ").append(cliente.getTelefono()).append(" | Correo: ").append(cliente.getCorreo()).append("\n");
        }
        info(sb.toString());
    }

    private static void mostrarClientePorId(Empresa empresa) {
        String identificacion = pedirTexto("Identificación del cliente:");
        if (identificacion == null) {
            return;
        }
        Cliente cliente = empresa.buscarCliente(identificacion.trim());
        if (cliente == null) {
            info("No se encontró el cliente.");
            return;
        }
        info("Identificación: " + cliente.getIdentificacion() + "\nNombre: " + cliente.getNombreCompleto()
                + "\nTeléfono: " + cliente.getTelefono() + "\nCorreo: " + cliente.getCorreo());
    }

    private static void eliminarCliente(Empresa empresa) {
        String identificacion = pedirTexto("Identificación del cliente a eliminar:");
        if (identificacion == null) {
            return;
        }
        if (!pedirBooleano("¿Seguro que desea eliminar al cliente " + identificacion + "?")) {
            return;
        }
        boolean ok = empresa.eliminarCliente(identificacion.trim());
        info(ok ? "Cliente eliminado con éxito." : "No se encontró el cliente.");
    }

    // ---------- Operador ----------

    private static void gestionarOperador(Empresa empresa) {
        int opeOption;
        do {
            opeOption = leerOpcion("~~ GESTIONAR OPERADOR ~~\n" +
                    "Seleccione una opcion\n" +
                    "\n 1. Registrar Operador" +
                    "\n 2. Mostrar Operadores" +
                    "\n 3. Mostrar Operador por ID" +
                    "\n 4. Actualizar Operador " +
                    "\n 5. Eliminar Operador" +
                    "\n 0. Regresar");
            switch (opeOption) {
                case 1:
                    registrarOperador(empresa);
                    break;
                case 2:
                    mostrarOperadores(empresa);
                    break;
                case 3:
                    mostrarOperadorPorId(empresa);
                    break;
                case 4:
                    actualizarOperador(empresa);
                    break;
                case 5:
                    eliminarOperador(empresa);
                    break;
                case 0:
                    break;
                default:
                    info("Opción no válida.");
            }
        } while (opeOption != 0);
    }

    private static void registrarOperador(Empresa empresa) {
        String identificacion = pedirTexto("Identificación del operador:");
        if (identificacion == null || identificacion.isBlank()) {
            info("Registro cancelado.");
            return;
        }
        String nombre = pedirTexto("Nombre completo:");
        String certificacion = pedirTexto("Certificación:");
        Integer anios = pedirEntero("Años de experiencia:");
        if (nombre == null || certificacion == null || anios == null) {
            info("Registro cancelado.");
            return;
        }
        boolean ok = empresa.registrarOperador(new Operador(identificacion.trim(), nombre, certificacion, anios));
        info(ok ? "Operador registrado con éxito." : "Ya existe un operador con esa identificación.");
    }

    private static void mostrarOperadores(Empresa empresa) {
        if (empresa.getOperadores().isEmpty()) {
            info("No hay operadores registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Operador operador : empresa.getOperadores()) {
            sb.append(operador.getIdentificacion()).append(" - ").append(operador.getNombreCompleto())
                    .append(" | Certificación: ").append(operador.getCertificacion())
                    .append(" | Años exp.: ").append(operador.getAniosExperiencia()).append("\n");
        }
        info(sb.toString());
    }

    private static void mostrarOperadorPorId(Empresa empresa) {
        String identificacion = pedirTexto("Identificación del operador:");
        if (identificacion == null) {
            return;
        }
        Operador operador = empresa.buscarOperador(identificacion.trim());
        if (operador == null) {
            info("No se encontró el operador.");
            return;
        }
        info("Identificación: " + operador.getIdentificacion() + "\nNombre: " + operador.getNombreCompleto()
                + "\nCertificación: " + operador.getCertificacion() + "\nAños de experiencia: " + operador.getAniosExperiencia());
    }

    private static void actualizarOperador(Empresa empresa) {
        String identificacion = pedirTexto("Identificación del operador a actualizar:");
        if (identificacion == null) {
            return;
        }
        if (empresa.buscarOperador(identificacion.trim()) == null) {
            info("No se encontró el operador.");
            return;
        }
        String certificacion = pedirTexto("Nueva certificación:");
        Integer anios = pedirEntero("Nuevos años de experiencia:");
        if (certificacion == null || anios == null) {
            info("Actualización cancelada.");
            return;
        }
        boolean ok = empresa.actualizarOperador(identificacion.trim(), certificacion, anios);
        info(ok ? "Operador actualizado con éxito." : "No se pudo actualizar el operador.");
    }

    private static void eliminarOperador(Empresa empresa) {
        String identificacion = pedirTexto("Identificación del operador a eliminar:");
        if (identificacion == null) {
            return;
        }
        if (!pedirBooleano("¿Seguro que desea eliminar al operador " + identificacion + "?")) {
            return;
        }
        boolean ok = empresa.eliminarOperador(identificacion.trim());
        info(ok ? "Operador eliminado con éxito." : "No se encontró el operador.");
    }

    // ---------- Reserva ----------

    private static void gestionarReserva(Empresa empresa) {
        int resOption;
        do {
            resOption = leerOpcion("~~ GESTIONAR RESERVAS ~~\n" +
                    "Seleccione una opcion\n" +
                    "\n 1. Crear Reserva" +
                    "\n 2. Agregar Equipo a Reserva" +
                    "\n 3. Asignar Operador a Reserva" +
                    "\n 4. Mostrar Reservas" +
                    "\n 5. Mostrar Reserva por Codigo" +
                    "\n 6. Consultar Valor Total de Reserva" +
                    "\n 7. Consultar Reservas Confirmadas por Cliente" +
                    "\n 8. Confirmar Reserva" +
                    "\n 9. Finalizar Reserva" +
                    "\n 10. Cancelar Reserva" +
                    "\n 0. Regresar");
            switch (resOption) {
                case 1:
                    crearReserva(empresa);
                    break;
                case 2:
                    agregarEquipoAReservaMenu(empresa);
                    break;
                case 3:
                    asignarOperadorAReservaMenu(empresa);
                    break;
                case 4:
                    mostrarReservas(empresa);
                    break;
                case 5:
                    mostrarReservaPorCodigo(empresa);
                    break;
                case 6:
                    consultarValorTotalReserva(empresa);
                    break;
                case 7:
                    consultarReservasConfirmadasPorCliente(empresa);
                    break;
                case 8:
                    confirmarReserva(empresa);
                    break;
                case 9:
                    finalizarReserva(empresa);
                    break;
                case 10:
                    cancelarReserva(empresa);
                    break;
                case 0:
                    break;
                default:
                    info("Opción no válida.");
            }
        } while (resOption != 0);
    }

    private static void crearReserva(Empresa empresa) {
        String codigo = pedirTexto("Código de la reserva:");
        if (codigo == null || codigo.isBlank()) {
            info("Creación cancelada.");
            return;
        }
        if (empresa.buscarReserva(codigo.trim()) != null) {
            info("Ya existe una reserva con ese código.");
            return;
        }
        String idCliente = pedirTexto("Identificación del cliente:");
        if (idCliente == null) {
            return;
        }
        Cliente cliente = empresa.buscarCliente(idCliente.trim());
        if (cliente == null) {
            info("No se encontró el cliente.");
            return;
        }
        LocalDate fecha = pedirFecha("Fecha de la reserva");
        if (fecha == null) {
            info("Creación cancelada.");
            return;
        }
        Turno turno = pedirTurno();
        if (turno == null) {
            info("Creación cancelada.");
            return;
        }

        Reserva reserva = new Reserva(codigo.trim(), cliente, fecha, turno);
        if (!empresa.registrarReserva(reserva)) {
            info("No se pudo registrar la reserva.");
            return;
        }
        info("Reserva creada con éxito (estado inicial: SOLICITADA). Ahora agregue los equipos.");

        agregarEquiposLoop(empresa, codigo.trim());

        if (reserva.contieneDron() && pedirBooleano("La reserva incluye un dron. ¿Desea asignar un operador ahora?")) {
            asignarOperadorReserva(empresa, codigo.trim());
        }
    }

    private static void agregarEquiposLoop(Empresa empresa, String codigoReserva) {
        boolean agregarMas = true;
        while (agregarMas) {
            String codEquipo = pedirTexto("Código del equipo a agregar (cancelar para terminar):");
            if (codEquipo == null) {
                break;
            }
            Integer horas = pedirEntero("Horas de uso:");
            if (horas == null) {
                info("No se agregó el equipo.");
            } else {
                boolean ok = empresa.agregarEquipoAReserva(codigoReserva, codEquipo.trim(), horas);
                info(ok ? "Equipo agregado a la reserva."
                        : "No se pudo agregar el equipo (verifique que exista, esté disponible, "
                        + "no esté ya incluido en la reserva y no esté ocupado en esa fecha/turno).");
            }
            agregarMas = pedirBooleano("¿Desea agregar otro equipo?");
        }
    }

    private static void agregarEquipoAReservaMenu(Empresa empresa) {
        String codigo = pedirTexto("Código de la reserva:");
        if (codigo == null) {
            return;
        }
        Reserva reserva = empresa.buscarReserva(codigo.trim());
        if (reserva == null) {
            info("No se encontró la reserva.");
            return;
        }
        if (reserva.getEstado() != EstadoReserva.SOLICITADA) {
            info("Solo se pueden agregar equipos a reservas en estado SOLICITADA.");
            return;
        }
        agregarEquiposLoop(empresa, codigo.trim());
    }

    private static void asignarOperadorReserva(Empresa empresa, String codigoReserva) {
        Reserva reserva = empresa.buscarReserva(codigoReserva);
        if (reserva != null && reserva.getOperador() != null) {
            info("Esta reserva ya tiene un operador asignado (" + reserva.getOperador().getNombreCompleto()
                    + "). Solo puede existir un operador por reserva.");
            return;
        }
        String idOperador = pedirTexto("Identificación del operador:");
        if (idOperador == null) {
            return;
        }
        boolean ok = empresa.asignarOperadorAReserva(codigoReserva, idOperador.trim());
        info(ok ? "Operador asignado con éxito."
                : "No se pudo asignar el operador (verifique que exista y que la reserva esté en estado SOLICITADA).");
    }

    private static void asignarOperadorAReservaMenu(Empresa empresa) {
        String codigo = pedirTexto("Código de la reserva:");
        if (codigo == null) {
            return;
        }
        if (empresa.buscarReserva(codigo.trim()) == null) {
            info("No se encontró la reserva.");
            return;
        }
        asignarOperadorReserva(empresa, codigo.trim());
    }

    private static void consultarValorTotalReserva(Empresa empresa) {
        String codigo = pedirTexto("Código de la reserva:");
        if (codigo == null) {
            return;
        }
        if (empresa.buscarReserva(codigo.trim()) == null) {
            info("No se encontró la reserva.");
            return;
        }
        double valor = empresa.consultarValorReserva(codigo.trim());
        info("Valor total de la reserva " + codigo.trim() + ": " + valor);
    }

    private static void mostrarReservas(Empresa empresa) {
        if (empresa.getReservas().isEmpty()) {
            info("No hay reservas registradas.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Reserva reserva : empresa.getReservas()) {
            sb.append(reserva.getCodigo()).append(" - ").append(reserva.getCliente().getNombreCompleto())
                    .append(" | ").append(reserva.getFecha()).append(" - ").append(reserva.getTurno())
                    .append(" | Estado: ").append(reserva.getEstado())
                    .append(" | Total: ").append(reserva.calcularValorTotal()).append("\n");
        }
        info(sb.toString());
    }

    private static void mostrarReservaPorCodigo(Empresa empresa) {
        String codigo = pedirTexto("Código de la reserva:");
        if (codigo == null) {
            return;
        }
        Reserva reserva = empresa.buscarReserva(codigo.trim());
        if (reserva == null) {
            info("No se encontró la reserva.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Código: ").append(reserva.getCodigo()).append("\n");
        sb.append("Cliente: ").append(reserva.getCliente().getNombreCompleto()).append("\n");
        sb.append("Fecha: ").append(reserva.getFecha()).append("\n");
        sb.append("Turno: ").append(reserva.getTurno()).append("\n");
        sb.append("Estado: ").append(reserva.getEstado()).append("\n");
        sb.append("Operador: ").append(reserva.getOperador() == null ? "Sin asignar" : reserva.getOperador().getNombreCompleto()).append("\n");
        sb.append("Equipos:\n");
        if (reserva.getDetalles().isEmpty()) {
            sb.append(" (sin equipos)\n");
        }
        for (DetalleReserva detalle : reserva.getDetalles()) {
            sb.append(" - ").append(detalle.getEquipo().getCodigo()).append(" (").append(detalle.getHoras())
                    .append(" h) subtotal: ").append(detalle.calcularSubtotal()).append("\n");
        }
        sb.append("Valor total: ").append(reserva.calcularValorTotal());
        info(sb.toString());
    }

    private static void confirmarReserva(Empresa empresa) {
        String codigo = pedirTexto("Código de la reserva a confirmar:");
        if (codigo == null) {
            return;
        }
        boolean ok = empresa.cambiarEstadoReserva(codigo.trim(), EstadoReserva.CONFIRMADA);
        info(ok ? "Reserva confirmada con éxito." : "No se pudo confirmar la reserva (verifique equipos/operador/estado).");
    }

    private static void cancelarReserva(Empresa empresa) {
        String codigo = pedirTexto("Código de la reserva a cancelar:");
        if (codigo == null) {
            return;
        }
        if (!pedirBooleano("¿Seguro que desea cancelar la reserva " + codigo + "?")) {
            return;
        }
        boolean ok = empresa.cambiarEstadoReserva(codigo.trim(), EstadoReserva.CANCELADA);
        info(ok ? "Reserva cancelada con éxito." : "No se pudo cancelar la reserva.");
    }

    private static void finalizarReserva(Empresa empresa) {
        String codigo = pedirTexto("Código de la reserva a finalizar:");
        if (codigo == null) {
            return;
        }
        boolean ok = empresa.cambiarEstadoReserva(codigo.trim(), EstadoReserva.FINALIZADA);
        info(ok ? "Reserva finalizada con éxito." : "No se pudo finalizar la reserva (solo se puede finalizar una reserva CONFIRMADA).");
    }

    private static void consultarReservasConfirmadasPorCliente(Empresa empresa) {
        String identificacion = pedirTexto("Identificación del cliente:");
        if (identificacion == null) {
            return;
        }
        if (empresa.buscarCliente(identificacion.trim()) == null) {
            info("No se encontró el cliente.");
            return;
        }
        ArrayList<Reserva> confirmadas = empresa.consultarReservasConfirmadas(identificacion.trim());
        if (confirmadas.isEmpty()) {
            info("El cliente no tiene reservas confirmadas.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Reserva reserva : confirmadas) {
            sb.append(reserva.getCodigo()).append(" | ").append(reserva.getFecha()).append(" - ")
                    .append(reserva.getTurno()).append(" | Total: ").append(reserva.calcularValorTotal()).append("\n");
        }
        info(sb.toString());
    }
}