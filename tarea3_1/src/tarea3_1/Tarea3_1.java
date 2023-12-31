/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea3_1;

import javax.swing.JOptionPane;

/**
 *
 * @author Lissette
 */

/**
 Un negocio desea llevar un registro de sus ventas mensuales. Cada mes, se ingresan las ventas
totales. Tu tarea es crear un programa que permita ingresar las ventas de cada mes en un
arreglo unidimensional y luego calcule lo siguiente:
• El promedio de ventas anual.
• El mes con las ventas más altas.
• El mes con las ventas más bajas
*/
public class Tarea3_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[] ventas_mensuales = new double[12];

        for (int i = 0; i < 12; i++) {
            String input = JOptionPane.showInputDialog("Ingrese las ventas del mes " + (i + 1) + ":");
            ventas_mensuales[i] = Double.parseDouble(input);
        }

        double totalVentasAnual = 0;
        for (double venta : ventas_mensuales) {
            totalVentasAnual += venta;
        }
        //calcular proedios, max, min de ventas
        double prom_anual = totalVentasAnual / 12;
        double ventas_max = ventas_mensuales[0];
        double ventas_min = ventas_mensuales[0];
        int mes_max = 1;
        int mes_min = 1;
        for (int i = 1; i < 12; i++) {
            //ventas minimas
            if (ventas_mensuales[i] > ventas_max) {
                ventas_max = ventas_mensuales[i];
                mes_max = i + 1;
            }
            //ventas maximas
            if (ventas_mensuales[i] < ventas_min) {
                ventas_min = ventas_mensuales[i];
                mes_min = i + 1;
            }
        }
        String mensaje = "El promedio de ventas anual: " + prom_anual + "\n";
        mensaje += "Mes con las ventas más altas: Mes " + mes_max + " con $" + ventas_max + "\n";
        mensaje += "Mes con las ventas más bajas: Mes " + mes_min + " con $" + ventas_min;
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
}
