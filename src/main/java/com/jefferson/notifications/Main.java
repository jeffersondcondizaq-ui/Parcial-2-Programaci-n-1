package com.jefferson.notifications;

import com.jefferson.pixelrent.model.Empresa;

import javax.swing.*;

public class Main {
    static void main() {

        Empresa pixelRent = new Empresa("900123456-7", "PixelRent", "Calle 10 # 20-30");

        int option = 0;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("~~ PIXELRENT 📷~~\n" +
                    "¿Que desea realizar hoy?\n" +
                    "\n 1. Gestionar Sede" +
                    "\n 2. Gestionar Equipo" +
                    "\n 3. Gestionar Cliente" +
                    "\n 4. Gestionar Operador" +
                    "\n 5. Gestionar Reserva" +
                    "\n 0. Salir del sistema"));

            switch (option) {
                case 1:
                    int sedeOption = 0;
                    do {
                        sedeOption = Integer.parseInt(JOptionPane.showInputDialog("~~ GESTIONAR SEDE ~~\n"+
                                "Seleccione una opcion\n" +
                                "\n 1. Registrar Sede" +
                                "\n 2. Mostrar Sedes" +
                                "\n 3. Mostrar Sede por Codigo" +
                                "\n 4. Actualizar Sede " +
                                "\n 5. Eliminar Sede" +
                                "\n 0. Regresar"));
                        switch (sedeOption) {
                            case 1:

                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:

                                break;
                            case 5:

                                break;
                            case 0:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no válida.");
                        }
                    } while (sedeOption != 0);
                    break;
                case 2:
                    int equOption = 0;
                    do {
                        equOption = Integer.parseInt(JOptionPane.showInputDialog("~~ D ~~\n" +
                                "¿Que desea realizar hoy?\n" +
                                "\n 1. Gestionar Camaras" +
                                "\n 2. Gestionar Drones" +
                                "\n 3. Gestionar Kits Iluminacion" +
                                "\n 0. Regresar"));
                        switch (equOption) {
                            case 1:
                                int camOption = 0;
                                do {
                                    camOption = Integer.parseInt(JOptionPane.showInputDialog("~~ GESTIONAR CAMARA ~~\n"+
                                            "Seleccione una opcion\n" +
                                            "\n 1. Registrar Camara" +
                                            "\n 2. Mostrar Camaras" +
                                            "\n 3. Mostrar Camara por Codigo" +
                                            "\n 4. Actualizar Camara " +
                                            "\n 5. Eliminar Camara" +
                                            "\n 0. Regresar"));
                                    switch (camOption) {
                                        case 1:

                                            break;
                                        case 2:

                                            break;
                                        case 3:

                                            break;
                                        case 4:

                                            break;
                                        case 5:

                                            break;
                                        case 0:
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida.");
                                    }
                                } while (camOption != 0);
                                break;
                            case 2:
                                int dronOption = 0;
                                do {
                                    dronOption = Integer.parseInt(JOptionPane.showInputDialog("~~ GESTIONAR DRON ~~\n"+
                                            "Seleccione una opcion\n" +
                                            "\n 1. Registrar Dron" +
                                            "\n 2. Mostrar Drones" +
                                            "\n 3. Mostrar Dron por Codigo" +
                                            "\n 4. Actualizar Dron " +
                                            "\n 5. Eliminar Dron" +
                                            "\n 0. Regresar"));
                                    switch (dronOption) {
                                        case 1:

                                            break;
                                        case 2:

                                            break;
                                        case 3:

                                            break;
                                        case 4:

                                            break;
                                        case 5:

                                            break;
                                        case 0:
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida.");
                                    }
                                } while (dronOption != 0);
                                break;
                            case 3:
                                int ilumOption = 0;
                                do {
                                    ilumOption = Integer.parseInt(JOptionPane.showInputDialog("~~ GESTIONAR KIT ILUMINACION ~~\n"+
                                            "Seleccione una opcion\n" +
                                            "\n 1. Registrar Kit Ilumnación" +
                                            "\n 2. Mostrar Kits Ilumnación" +
                                            "\n 3. Mostrar Kit Ilumnación por Codigo" +
                                            "\n 4. Actualizar Kit Ilumnación " +
                                            "\n 5. Eliminar Kit Ilumnación" +
                                            "\n 0. Regresar"));
                                    switch (ilumOption) {
                                        case 1:

                                            break;
                                        case 2:

                                            break;
                                        case 3:

                                            break;
                                        case 4:

                                            break;
                                        case 5:

                                            break;
                                        case 0:
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida.");
                                    }
                                } while (ilumOption != 0);
                                break;
                            case 0:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no válida.");
                        }
                    } while (equOption != 0);
                    break;
                case 3:
                    int clieOption = 0;
                    do {
                        clieOption = Integer.parseInt(JOptionPane.showInputDialog("~~ GESTIONAR CLIENTE ~~\n"+
                                "Seleccione una opcion\n" +
                                "\n 1. Registrar Cliente" +
                                "\n 2. Mostrar Cliente" +
                                "\n 3. Mostrar Cliente por ID" +
                                "\n 4. Eliminar Cliente" +
                                "\n 0. Regresar"));
                        switch (clieOption) {
                            case 1:

                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:

                                break;
                            case 0:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no válida.");
                        }
                    } while (clieOption != 0);
                    break;
                case 4:
                    int opeOption = 0;
                    do {
                        opeOption = Integer.parseInt(JOptionPane.showInputDialog("~~ GESTIONAR OPERADOR ~~\n"+
                                "Seleccione una opcion\n" +
                                "\n 1. Registrar Operador" +
                                "\n 2. Mostrar Operadores" +
                                "\n 3. Mostrar Operador por ID" +
                                "\n 4. Actualizar Operador " +
                                "\n 5. Eliminar Operador" +
                                "\n 0. Regresar"));
                        switch (opeOption) {
                            case 1:

                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:

                                break;
                            case 5:

                                break;
                            case 0:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no válida.");
                        }
                    } while (opeOption != 0);
                    break;
                case 5:
                    int resOption = 0;
                    do {
                        resOption = Integer.parseInt(JOptionPane.showInputDialog("~~ GESTIONAR CAMARAS ~~\n"+
                                "Seleccione una opcion\n" +
                                "\n 1. Crear Reserva" +
                                "\n 2. Mostrar Reservas" +
                                "\n 3. Mostrar Reserva por Codigo" +
                                "\n 4. Confirmar Reserva" +
                                "\n 5. Cancelar Reserva" +
                                "\n 0. Regresar"));
                        switch (resOption) {
                            case 1:

                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:

                                break;
                            case 5:

                                break;
                            case 0:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no válida.");
                        }
                    } while (resOption != 0);
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (option != 0);
    }
}
