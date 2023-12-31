/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea3_2;

import javax.swing.JOptionPane;

/**
 *
 * @author Lissette
 */

/*
Se te proporciona un conjunto de datos meteorológicos con las temperaturas diarias durante un
período. Tu misión es crear un programa que realice lo siguiente:
• Almacenar las temperaturas en un arreglo unidimensional.
• Calcular y mostrar la temperatura promedio.
• Encontrar y mostrar la temperatura más alta y la más baja registradas durante el períod
*/
public class Tarea3_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int dias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de días:"));
        double[] temperaturas_dias = new double[dias];
        for (int i = 0; i < dias; i++) {
            String input = JOptionPane.showInputDialog("Ingrese la temperatura del día " + (i + 1) + ":");
            temperaturas_dias[i] = Double.parseDouble(input);
        }
        double suma_temperaturas = 0;
        for (double temperatura : temperaturas_dias) {
            suma_temperaturas += temperatura;
        }
        double prom_temperatura = suma_temperaturas / dias;
        double temperatura_max = temperaturas_dias[0];
        double temperatura_min = temperaturas_dias[0];
        for (int i = 1; i < dias; i++) {
            if (temperaturas_dias[i] > temperatura_max) {
                temperatura_max = temperaturas_dias[i];
            }
            if (temperaturas_dias[i] < temperatura_min) {
                temperatura_min = temperaturas_dias[i];
            }
        }
        String mensaje = "Temperatura promedio: " + prom_temperatura + "\n";
        mensaje += "Temperatura más alta: " + temperatura_max + "°\n";
        mensaje += "Temperatura más baja: " + temperatura_min + "°";
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
}
