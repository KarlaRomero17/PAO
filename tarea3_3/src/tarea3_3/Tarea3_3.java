/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea3_3;

import javax.swing.JOptionPane;

/**
 *
 * @author Lissette
 */
public class Tarea3_3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        var  dimension = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la dimension de la matriz cuadrada: "));
        char[][] asientos = new char[dimension][dimension];

        // Inicializar todos los asientos como disponibles ('O' representa disponible, 'X' representa ocupado)
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                asientos[i][j] = 'O';
            }
        }
        // Menu
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "MENU\n" +
                    "1. Mostrar estado de asientos\n" +
                    "2. Reservar asiento\n" +
                    "3. Liberar asiento\n" +
                    "4. Salir\n\n" +
                    "Ingrese el número de la opción:");

            if (opcion == null) {
                break; // Salir del programa si se presiona Cancelar o se cierra la ventana
            }

            switch (opcion) {
                case "1":
                    mostrarEstadoAsientos(asientos);
                    break;
                case "2":
                    reservarAsiento(asientos);
                    break;
                case "3":
                    liberarAsiento(asientos);
                    break;
                case "4":
                    System.exit(0); // Salir del programa
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Ingrese un número del 1 al 4.");
            }
        }
    }

    // mostrar el estado actual de los asientos
    private static void mostrarEstadoAsientos(char[][] asientos) {
        StringBuilder estado = new StringBuilder("Estado de los asientos:\n\n");
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                estado.append(asientos[i][j]).append(" ");
            }
            estado.append("\n");
        }
        JOptionPane.showMessageDialog(null, estado.toString());
    }

    //reservar un asiento
    private static void reservarAsiento(char[][] asientos) {
        int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de fila (1-" + asientos.length + "):")) - 1;
        int columna = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de columna (1-" + asientos[0].length + "):")) - 1;

        if (fila >= 0 && fila < asientos.length && columna >= 0 && columna < asientos[0].length) {
            if (asientos[fila][columna] == 'O') {
                asientos[fila][columna] = 'X';
                JOptionPane.showMessageDialog(null, "¡Asiento reservado con éxito!");
            } else {
                JOptionPane.showMessageDialog(null, "El asiento ya está ocupado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila o columna no válida.");
        }
    }
    // liberar un asiento
    private static void liberarAsiento(char[][] asientos) {
        int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de fila (1-" + asientos.length + "):")) - 1;
        int columna = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de columna (1-" + asientos[0].length + "):")) - 1;

        if (fila >= 0 && fila < asientos.length && columna >= 0 && columna < asientos[0].length) {
            if (asientos[fila][columna] == 'X') {
                asientos[fila][columna] = 'O';
                JOptionPane.showMessageDialog(null, "¡Asiento liberado con éxito!");
            } else {
                JOptionPane.showMessageDialog(null, "El asiento ya está disponible.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila o columna no válida.");
        }
    }
}
